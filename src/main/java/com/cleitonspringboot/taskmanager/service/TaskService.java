package com.cleitonspringboot.taskmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cleitonspringboot.taskmanager.dto.TaskRequestDTO;
import com.cleitonspringboot.taskmanager.dto.TaskResponseDTO;
import com.cleitonspringboot.taskmanager.entity.Task;
import com.cleitonspringboot.taskmanager.entity.User;
import com.cleitonspringboot.taskmanager.enums.TaskStatus;
import com.cleitonspringboot.taskmanager.exception.BusinessException;
import com.cleitonspringboot.taskmanager.repository.TaskRepository;
import com.cleitonspringboot.taskmanager.repository.UserRepository;
import com.cleitonspringboot.taskmanager.security.SecurityUserDetails;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskResponseDTO create(TaskRequestDTO dto) {
        User user = getAuthenticatedUser();

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());

        if (dto.getStatus() != null) {
            task.setStatus(dto.getStatus());
        } else {
            task.setStatus(TaskStatus.PENDENTE);
        }

        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        return toResponseDTO(savedTask);
    }

    public List<TaskResponseDTO> findAllFromAuthenticatedUser() {
        User user = getAuthenticatedUser();

        return taskRepository.findByUser(user)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TaskResponseDTO findById(Long id) {
        User user = getAuthenticatedUser();

        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new BusinessException("Tarefa não encontrada"));

        return toResponseDTO(task);
    }

    public TaskResponseDTO update(Long id, TaskRequestDTO dto) {
        User user = getAuthenticatedUser();

        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new BusinessException("Tarefa não encontrada"));

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());

        if (dto.getStatus() != null) {
            task.setStatus(dto.getStatus());
        }

        Task updatedTask = taskRepository.save(task);

        return toResponseDTO(updatedTask);
    }

    public void delete(Long id) {
        User user = getAuthenticatedUser();

        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new BusinessException("Tarefa não encontrada"));

        taskRepository.delete(task);
    }

    private User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof SecurityUserDetails)) {
            throw new BusinessException("Usuário não autenticado");
        }

        SecurityUserDetails userDetails = (SecurityUserDetails) principal;
        String email = userDetails.getUsername();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Usuário autenticado não encontrado"));
    }

    private TaskResponseDTO toResponseDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getUser().getId(),
                task.getUser().getName());
    }
}