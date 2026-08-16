package com.taskinator5000_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Taskinator5000ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Taskinator5000ApiApplication.class, args);
	}

}