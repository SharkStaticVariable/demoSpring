package com.example.demo.service;

import com.example.demo.dto.AccountsDTO;
import com.example.demo.entity.AccountsEntity;
import com.example.demo.repository.AccountsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountsService {
    private final AccountsRepository accountsRepository;

    public AccountsEntity save(AccountsEntity accountsEntity) {
       return accountsRepository.save(AccountsEntity.builder()
                .balance(accountsEntity.getBalance())
                .isActive(accountsEntity.isActive())
                .isLocked(accountsEntity.isLocked())
                .build());
    }
    public List<AccountsEntity> readAll() {
        return accountsRepository.findAll();
    }

    public AccountsEntity update(AccountsEntity accountsEntity) {
        return accountsRepository.save(accountsEntity);
    }

    public void delete(Integer id) {
        accountsRepository.deleteById(id);
    }
}
