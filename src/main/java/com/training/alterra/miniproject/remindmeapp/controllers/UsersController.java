package com.training.alterra.miniproject.remindmeapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UsersController {

    @GetMapping("/users/{name}")
    public void index(@PathVariable(name = "name") String name) {
        System.out.println("Hello " + name);
    }
}
