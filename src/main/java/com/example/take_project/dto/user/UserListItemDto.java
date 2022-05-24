package com.example.take_project.dto.user;

import com.example.take_project.models.UserRole;

public class UserListItemDto {
    private String name;
    private UserRole userRole;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
