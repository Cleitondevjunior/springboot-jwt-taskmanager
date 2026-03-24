package com.cleitonspringboot.taskmanager.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class LogService {

    public void info(String action, String userEmail, String details) {
        System.out.println("[" + LocalDateTime.now() + "] ACTION=" + action
                + " USER=" + userEmail
                + " DETAILS=" + details);
    }
}