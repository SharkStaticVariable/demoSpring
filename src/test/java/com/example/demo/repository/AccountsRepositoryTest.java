package com.example.demo.repository;

import com.example.demo.IntegrationBaseTest;
import com.example.demo.entity.AccountsEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountsRepositoryTest extends IntegrationBaseTest {
private static final Integer hundred_ID = 1;
    @Autowired
    private AccountsRepository accountsRepository;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void testGetById(){
    Optional<AccountsEntity> accounts = accountsRepository.findById(hundred_ID);
    assertTrue(accounts.isPresent());
    accounts.ifPresent(entity -> { assertEquals(1000, entity.getBalance());});
    }

    @Test
    void testSave(){
        AccountsEntity accounts = AccountsEntity.builder().balance(20000).build();
        accountsRepository.save(accounts);
        assertNotNull(accounts.getId());
    }
}