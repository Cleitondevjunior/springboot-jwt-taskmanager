package com.cleitonspringboot.taskmanager.repository;

//Permite: buscar usuário por email,verificar se email já existe


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleitonspringboot.taskmanager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
