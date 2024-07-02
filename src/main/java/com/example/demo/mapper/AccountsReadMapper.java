package com.example.demo.mapper;

import com.example.demo.dto.AccountsDTO;
import com.example.demo.entity.AccountsEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountsReadMapper implements Mapper<AccountsEntity, AccountsDTO>{
    @Override
    public AccountsDTO map(AccountsEntity object) {
        return new AccountsDTO(
                object.getId(),
                object.getBalance(),
                object.getNumber(),
                object.isActive()
        );
    }
}
