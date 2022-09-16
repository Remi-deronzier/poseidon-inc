package com.nnk.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Username is mandatory")
    @Column(nullable = false)
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one upercase letter, one lowercase letter at least one number and one symbol and must be at least 8 characters")
    @Column(nullable = false)
    private String password;
    @NotBlank(message = "FullName is mandatory")
    @Column(nullable = false)
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    @Column(nullable = false)
    private String role;
}
