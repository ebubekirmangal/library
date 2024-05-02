package com.example.demo.entities;

import org.springframework.security.core.GrantedAuthority;


public enum UserRole implements GrantedAuthority {

    Employee,
    Visitor;

    @Override
    public String getAuthority() {
        return name();
    }
}
