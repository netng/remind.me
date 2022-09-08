/**
 * https://medium.com/codestorm/custom-json-response-with-responseentity-in-spring-boot-b09e87ab1f0a
 */
package com.training.alterra.miniproject.remindmeapp.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class SchedulesResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object reponseObj) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("data", reponseObj);
        map.put("status", status.value());
        map.put("message", message);

        return new ResponseEntity<Object>(map, status);
    }
}
