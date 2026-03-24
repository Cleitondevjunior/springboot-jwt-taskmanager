package com.cleitonspringboot.taskmanager.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cleitonspringboot.taskmanager.dto.AuthResponseDTO;
import com.cleitonspringboot.taskmanager.dto.LoginRequestDTO;
import com.cleitonspringboot.taskmanager.dto.RegisterRequestDTO;
import com.cleitonspringboot.taskmanager.entity.User;
import com.cleitonspringboot.taskmanager.enums.Role;
import com.cleitonspringboot.taskmanager.exception.BusinessException;
import com.cleitonspringboot.taskmanager.repository.UserRepository;
import com.cleitonspringboot.taskmanager.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final LogService logService;

    public AuthService(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            LogService logService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.logService = logService;
    }

    public AuthResponseDTO register(RegisterRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.ROLE_USER);

        User savedUser = userRepository.save(user);

        logService.info("REGISTER", savedUser.getEmail(), "Novo usuário cadastrado");

        String token = jwtService.generateToken(savedUser);

        return new AuthResponseDTO(
                token,
                "Bearer",
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole().name());
    }

    public AuthResponseDTO login(LoginRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        logService.info("LOGIN", user.getEmail(), "Usuário autenticado com sucesso");

        String token = jwtService.generateToken(user);

        return new AuthResponseDTO(
                token,
                "Bearer",
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name());
    }
}