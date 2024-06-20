package com.example.demo.dto;

import lombok.*;

@Value
@ToString
@Data
@Builder
@AllArgsConstructor
public class LoginDto {
    String username;
    String password;
}
