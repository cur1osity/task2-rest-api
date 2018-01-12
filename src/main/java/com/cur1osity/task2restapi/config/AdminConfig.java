package com.cur1osity.task2restapi.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {

    @Value ("${admin.mail}")
    private String adminMail;

    @Value("${second.mail}")
    private String secondEmail;
}