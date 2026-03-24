package com.cleitonspringboot.taskmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleitonspringboot.taskmanager.dto.UserResponseDTO;
import com.cleitonspringboot.taskmanager.entity.User;
import com.cleitonspringboot.taskmanager.exception.BusinessException;
import com.cleitonspringboot.taskmanager.repository.UserRepository;
import com.cleitonspringboot.taskmanager.security.SecurityUserDetails;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof SecurityUserDetails)) {
            throw new BusinessException("Usuário não autenticado");
        }

        SecurityUserDetails userDetails = (SecurityUserDetails) principal;

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        UserResponseDTO dto = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name());

        return ResponseEntity.ok(dto);
    }
}