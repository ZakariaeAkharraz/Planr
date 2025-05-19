package com.learning.planr.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_seq")
    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;

    @ManyToOne
    @JoinColumn(name="projects.id")
    private Project project;
}
