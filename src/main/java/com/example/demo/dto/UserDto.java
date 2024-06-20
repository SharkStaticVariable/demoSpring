package com.example.demo.dto;

import com.example.demo.entity.AccountsEntity;
import com.example.demo.entity.RolesEntity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
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

     public UserDto(String username, String firstName, String lastName, int age, String phoneNumber, String address, String documentNumber, String email, String password, AccountsEntity accounts, RolesEntity roles) {
     }

     public String getUsername() {
          return username;
     }

     public String getFirstName() {
          return firstName;
     }

     public String getLastName() {
          return lastName;
     }

     public int getAge() {
          return age;
     }

     public String getPhoneNumber() {
          return phoneNumber;
     }

     public String getAddress() {
          return address;
     }

     public String getDocumentNumber() {
          return documentNumber;
     }

     public String getEmail() {
          return email;
     }

     public String getPassword() {
          return password;
     }

     public AccountsEntity getAccounts() {
          return accounts;
     }

     public RolesEntity getRoles() {
          return roles;
     }
}