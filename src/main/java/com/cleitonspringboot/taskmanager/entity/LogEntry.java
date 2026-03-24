package com.cleitonspringboot.taskmanager.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class LogEntry {

    @Id
    private String id;

    private String action;
    private String userEmail;
    private String details;
    private LocalDateTime createdAt;

    // Construtor vazio
    public LogEntry() {
    }

    // Construtor completo
    public LogEntry(String id, String action, String userEmail, String details, LocalDateTime createdAt) {
        this.id = id;
        this.action = action;
        this.userEmail = userEmail;
        this.details = details;
        this.createdAt = createdAt;
    }

    // Método de criação (substitui o builder)
    public static LogEntry create(String action, String userEmail, String details) {
        LogEntry log = new LogEntry();
        log.setAction(action);
        log.setUserEmail(userEmail);
        log.setDetails(details);
        log.setCreatedAt(LocalDateTime.now());
        return log;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}