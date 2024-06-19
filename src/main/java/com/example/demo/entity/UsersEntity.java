package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="username")
    private String username;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="age")
    private int age;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name="address")
    private String address;
    @Column(name = "documentnumber")
    private String documentNumber;
    @Column(name="email")
    private String email;
    @Transient
    transient private String confirmPassword;
    @Column(name="password")
    private String password;


    @ManyToOne
    @JoinColumn(name="accounts_id")
    private AccountsEntity accounts;
    @ManyToMany
    @JoinTable(name="users_roles", joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<RolesEntity> roles;
}
