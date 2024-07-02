package com.example.demo.dto;

import com.example.demo.entity.AccountsEntity;
import com.example.demo.entity.RolesEntity;
import lombok.Value;

@Value
public class UserReadDto {
    Integer id;
    String username;
    String firstName;
    String lastName;
    int age;
    String phoneNumber;
    String address;
    String middleName;
    String email;
    String password;
    RolesEntity roles;
}
