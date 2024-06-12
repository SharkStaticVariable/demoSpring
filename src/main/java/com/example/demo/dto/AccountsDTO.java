package com.example.demo.dto;

import lombok.Data;

@Data
public class AccountsDTO {
    private double balance;
    private boolean isActive;
    private boolean isLocked;

}
