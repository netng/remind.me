package com.training.alterra.miniproject.remindmeapp.services.schedules;

import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.schedules.ScheduleResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.Reminder;
import com.training.alterra.miniproject.remindmeapp.entities.Schedule;
import com.training.alterra.miniproject.remindmeapp.exceptions.InvalidReminderDateTimeException;
import com.training.alterra.miniproject.remindmeapp.exceptions.ResourceNotFoundException;
import com.training.alterra.miniproject.remindmeapp.jobs.ReminderJob;
import com.training.alterra.miniproject.remindmeapp.repositories.ReminderRepository;
import com.training.alterra.miniproject.remindmeapp.repositories.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ReminderRepository reminderRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private Scheduler scheduler;

    @Override
    public ScheduleResponseDTO createNewSchedule(Long reminderId, ScheduleRequestDTO requestDTO) {

        ZoneId reminderDateTime = reminderRepository.findById(reminderId).get().getUser().getTimeZone();
        Reminder reminderOfSchedule = reminderRepository.findById(reminderId).get();

        try {
            ZonedDateTime dateTime = ZonedDateTime.of(requestDTO.getReminderDateTime(), reminderDateTime);
            if (dateTime.isBefore(ZonedDateTime.now())) {
                throw new InvalidReminderDateTimeException(dateTime);
            }

            JobDetail jobDetail = buildJobDetail(reminderOfSchedule);
            Trigger trigger = buildJobTrigger((jobDetail), dateTime);
            scheduler.scheduleJob(jobDetail, trigger);

            Optional<Schedule> schedule = reminderRepository.findById(reminderId)
                    .map(reminder -> {
                        requestDTO.setReminder(reminder);
                        return scheduleRepository.save(convertToEntity(requestDTO));
                    });

            return convertToDto(schedule.get());

        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ScheduleResponseDTO> showAllSchedules(Long reminderId) {
        reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException(reminderId));

        List<Schedule> schedules = scheduleRepository.findByReminderId(reminderId);

        if (!schedules.isEmpty()) {
            return schedules.stream()
                    .map(schedule -> convertToDto(schedule))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    private Schedule convertToEntity(ScheduleRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Schedule.class);
    }

    private ScheduleResponseDTO convertToDto(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleResponseDTO.class);
    }


    private JobDetail buildJobDetail(Reminder reminder) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("reminderUser", reminder.getUser().getFullName());
        jobDataMap.put("reminderTitle", reminder.getTitle());
        jobDataMap.put("reminderDescription", reminder.getDescription());

        return JobBuilder.newJob(ReminderJob.class)
                .withIdentity(UUID.randomUUID().toString(), "reminder-jobs")
                .withDescription("Send Reminder Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "reminder-triggers")
                .withDescription("Send Reminder Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}
