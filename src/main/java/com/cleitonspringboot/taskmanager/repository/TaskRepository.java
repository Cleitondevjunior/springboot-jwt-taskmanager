package com.cleitonspringboot.taskmanager.repository;


//Permite:listar tarefas de um usuário, buscar tarefa específica do usuário, filtrar tarefas por status


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleitonspringboot.taskmanager.entity.Task;
import com.cleitonspringboot.taskmanager.entity.User;
import com.cleitonspringboot.taskmanager.enums.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);

    Optional<Task> findByIdAndUser(Long id, User user);

    List<Task> findByUserAndStatus(User user, TaskStatus status);
}
