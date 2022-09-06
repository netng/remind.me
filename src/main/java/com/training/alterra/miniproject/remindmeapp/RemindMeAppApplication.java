package com.training.alterra.miniproject.remindmeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RemindMeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemindMeAppApplication.class, args);
	}

}
