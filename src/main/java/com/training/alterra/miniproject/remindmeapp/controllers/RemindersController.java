/**
 * @Author Nandang Sopyan
 * @ApplicationName remind.me app
 * @CreatedAt Sept 2022
 * @Description This is a REST API application as mini project task at alterra training academy program
 */
package com.training.alterra.miniproject.remindmeapp.controllers;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.training.alterra.miniproject.remindmeapp.dtos.BaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.PaginatedBaseResponseDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderRequestDTO;
import com.training.alterra.miniproject.remindmeapp.dtos.reminders.ReminderResponseDTO;
import com.training.alterra.miniproject.remindmeapp.entities.Reminder;
import com.training.alterra.miniproject.remindmeapp.services.reminders.IReminderService;
import com.training.alterra.miniproject.remindmeapp.services.reminders.ReminderService;
import com.training.alterra.miniproject.remindmeapp.utils.ReminderExcelExporterUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1")
public class RemindersController {

    @Autowired
    IReminderService reminderService;

    @PostMapping("/users/{userId}/reminders")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BaseResponseDTO<String, String, ReminderResponseDTO>> createNewReminder(
            @PathVariable Long userId, @RequestBody ReminderRequestDTO requestDTO) {
        BaseResponseDTO<String, String, ReminderResponseDTO> responseDTO =
                reminderService.createNewReminder(userId, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/users/{userId}/reminders")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PaginatedBaseResponseDTO<String, String, List<ReminderResponseDTO>>> listAllReminders(
            @PathVariable Long userId, Pageable pageable) {
        PaginatedBaseResponseDTO<String, String, List<ReminderResponseDTO>> reminderResponseDTOS =
                reminderService.listAllReminders(userId, pageable);
        return ResponseEntity.ok().body(reminderResponseDTOS);
    }

    @DeleteMapping("/reminders/{reminderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<BaseResponseDTO<String, String, ReminderResponseDTO>> deleteReminder(
            @PathVariable Long reminderId) {
        BaseResponseDTO<String, String, ReminderResponseDTO> response = reminderService.deleteReminder(reminderId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/reminders/{reminderId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponseDTO<String, String, ReminderResponseDTO>> updateReminder(
            @PathVariable Long reminderId, @RequestBody ReminderRequestDTO requestDTO) {
        return ResponseEntity.ok().body(reminderService.updateReminder(reminderId, requestDTO));
    }

    @GetMapping("/reminders/{reminderId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponseDTO<String, String, ReminderResponseDTO>> showReminderDetail(
            @PathVariable Long reminderId) {
        return ResponseEntity.ok().body(reminderService.showReminderDetail(reminderId));
    }

    @GetMapping("/users/{userId}/reminders/exports")
    @ResponseStatus(HttpStatus.OK)
    public void ExportAllReminders(
            @PathVariable Long userId, Pageable pageable, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=reminders_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        Page<Reminder> listReminders = reminderService.exportToExcel(userId, pageable);

        ReminderExcelExporterUtil excelExporter = new ReminderExcelExporterUtil(listReminders);

        excelExporter.export(response);
    }
}