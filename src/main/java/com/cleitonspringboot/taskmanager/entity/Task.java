package com.cleitonspringboot.taskmanager.entity;


// Essa entidade representa cada tarefa do sistema.

import java.time.LocalDateTime;

import com.cleitonspringboot.taskmanager.enums.TaskPriority;
import com.cleitonspringboot.taskmanager.enums.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Task {

	//identificador do usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //título da tarefa
    @Column(nullable = false, length = 150)
    private String title;

    //descrição
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    //pendente, em andamento, concluída
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TaskStatus status;

    //baixa, média, alta
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TaskPriority priority;

    //data de criação
    @Column(nullable = false)
    private LocalDateTime createdAt;

    //data de atualização
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    //dono da tarefa
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //Os métodos @PrePersist e @PreUpdate ajudam a preencher as datas automaticamente.
    
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = TaskStatus.PENDENTE;
        }

        if (this.priority == null) {
            this.priority = TaskPriority.MEDIA;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
