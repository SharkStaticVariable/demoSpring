package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name="accounts")
public class AccountsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double balance;
    @Column(name = "isactive")
    private boolean isActive;
    @Column(name = "islocked")
    private boolean isLocked;

    public AccountsEntity(double balance, boolean isActive, boolean isLocked) {
        this.balance = balance;
        this.isActive = isActive;
        this.isLocked = isLocked;
    }
    public AccountsEntity(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
