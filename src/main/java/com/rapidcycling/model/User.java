package com.rapidcycling.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users", indexes = @Index(name = "uk_users_username", columnList = "username", unique = true))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String name;
    
    @NotBlank @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String surname;

    @NotBlank @Size(max = 50)
    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @NotBlank @Size(max = 255)
    @Column(nullable = false, length = 255)
    @JsonIgnore
    private String password;

    @NotBlank @Email @Size(max = 254)
    @Column(nullable = false, length = 254, unique = true)
    private String email;
    
    @Size(max = 2048)
    @Column(length = 2048)
    private String photo;

}
