package com.cleitonspringboot.taskmanager.entity;

//Essa entidade Ela representa o usuário do sistema.

import java.util.ArrayList;
import java.util.List;

import com.cleitonspringboot.taskmanager.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class User {

	//identificador do usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //nome
    @Column(nullable = false, length = 120)
    private String name;

    //email único
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    //senha criptografada(implementar depois)
    @Column(nullable = false)
    private String password;

    //perfil do usuário
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role role;

    //lista de tarefas daquele usuário
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();
}