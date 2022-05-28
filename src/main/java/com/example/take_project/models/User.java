package com.example.take_project.models;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private byte[] passwordHash;
    @Column(unique = true)
    private byte[] salt;

    @ElementCollection(targetClass = UserRole.class)
    @JoinTable(name = "roles", joinColumns = @JoinColumn(name = "id"))
    @Column(name="roles", nullable = false)
    @Enumerated(EnumType.STRING)
    private Collection<UserRole> userRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
