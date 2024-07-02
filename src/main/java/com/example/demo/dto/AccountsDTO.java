package com.example.demo.dto;

import lombok.Data;

@Data
public class AccountsDTO {
    private double balance;
    private boolean isActive;
    private int number;

    public AccountsDTO(Integer id, double balance, int number, boolean active) {
    }

}
