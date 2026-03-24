package com.cleitonspringboot.taskmanager.dto;

//Esse DTO será usado para criar e atualizar tarefa.

import com.cleitonspringboot.taskmanager.enums.TaskPriority;
import com.cleitonspringboot.taskmanager.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 150, message = "O título deve ter entre 3 e 150 caracteres")
    private String title;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 5, message = "A descrição deve ter no mínimo 5 caracteres")
    private String description;

    @NotNull(message = "A prioridade é obrigatória")
    private TaskPriority priority;

    private TaskStatus status;

    public TaskRequestDTO() {
    }

    public TaskRequestDTO(String title, String description, TaskPriority priority, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}