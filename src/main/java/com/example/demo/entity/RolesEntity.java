package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;



public enum RolesEntity implements GrantedAuthority//
 {
  USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
