package com.learning.planr.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_id_seq")
    private Long id;


    private String firstname;
    private String lastname;

    @Column(unique = true, length = 100, nullable = false)
    private String email;
    @Column( nullable = false)
    private String password;
    private String role;

    @ManyToMany()
    @JoinTable(name="user_project",
    joinColumns = @JoinColumn(name="users.id"),
    inverseJoinColumns = @JoinColumn(name="projects.id"))
    private List<Project> projects;


}
