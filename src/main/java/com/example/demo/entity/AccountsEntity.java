package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name="accounts")
public class AccountsEntity {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double balance;

    @Column(name = "isactive")
    private boolean isActive;

    private int number;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id") // Здесь указываем имя столбца исходной таблицы и имя колонки
    @JsonIgnore
    private UsersEntity user;

    public AccountsEntity(double balance, boolean isActive, int number) {
        this.balance = balance;
        this.isActive = isActive;
        this.number = number;
    }
    public AccountsEntity(){
    }

    public void setBalance(BigDecimal zero) {

    }
}