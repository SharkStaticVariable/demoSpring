package com.example.demo.service;

import com.example.demo.entity.AccountsEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.AccountsRepository;
import com.example.demo.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final UsersRepository usersRepository;
    @Autowired
    public AccountsService(AccountsRepository accountsRepository, UsersRepository usersRepository, UsersRepository userRepository) {
        this.accountsRepository = accountsRepository;
        this.usersRepository = usersRepository;
    }

    public AccountsEntity createAccountForUser(UsersEntity user) {
        // Проверяем, есть ли у пользователя уже счет
//        if (user.getAccount() != null) {
//            throw new IllegalStateException("User already has an account");
//        }

        AccountsEntity account = new AccountsEntity();
        account.setNumber(generateAccountNumber());
        account.setBalance(BigDecimal.ZERO);
        account.setUser(user);

        AccountsEntity savedAccount = accountsRepository.save(account);

        // Связываем счет с пользователем
//        user.setAccount(savedAccount);
        usersRepository.save(user);

        return savedAccount;
    }

    private int generateAccountNumber() {
        // Логика генерации номера счета
        Random random = new Random();
        return 100000 + random.nextInt(900000); // генерация случайного числа от 100000 до 999999
    }

    public AccountsEntity save(AccountsEntity accountsEntity) {
       return accountsRepository.save(AccountsEntity.builder()
                .balance(accountsEntity.getBalance())
                .isActive(accountsEntity.isActive())
                .number(accountsEntity.getNumber())
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
