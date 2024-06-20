package com.example.demo.dto;

import com.example.demo.entity.AccountsEntity;
import com.example.demo.entity.RolesEntity;

import lombok.Value;

@Value
public class UserDto {
     String username;
     String firstName;
     String lastName;
     int age;
     String phoneNumber;
     String address;
     String documentNumber;
     String email;
     String password;
     AccountsEntity accounts;
     RolesEntity roles;
}