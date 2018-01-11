package com.cur1osity.task2restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Task2RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Task2RestApiApplication.class, args);
	}
}
