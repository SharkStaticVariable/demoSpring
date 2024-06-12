package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private int age;
    @Column(name = "phonenumber")
    private String phoneNumber;
    private String address;
    @Column(name = "documentnumber")
    private String documentNumber;
    private String email;
    @ManyToOne
    @JoinColumn(name="accounts_id")
    private AccountsEntity accounts;
}
