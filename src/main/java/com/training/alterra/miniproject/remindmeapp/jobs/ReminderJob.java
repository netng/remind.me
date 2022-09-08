package com.training.alterra.miniproject.remindmeapp.jobs;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class ReminderJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String reminderUser = jobDataMap.getString("reminderUser");
        String reminderTitle = jobDataMap.getString("reminderTitle");
        String reminderDescription = jobDataMap.getString("reminderDescription");

        System.out.println(String.format(
                "Hello %s, I'm remindy :) \n" +
                        "Just reminding you that you have reminder : \n" +
                        "Title: %s \n" +
                        "Description: %s \n" +
                        "\n" +
                        "Cheers, \n" +
                        "Remindy from Remind.me" +
                        " :) \n",
                reminderUser,
                reminderTitle,
                reminderDescription


        ));
    }
}
