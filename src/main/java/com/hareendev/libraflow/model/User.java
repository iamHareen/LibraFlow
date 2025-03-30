package com.hareendev.libraflow.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data // for getter & setters
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

}
