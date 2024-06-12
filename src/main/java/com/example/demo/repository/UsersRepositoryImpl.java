package com.example.demo.repository;

import com.example.demo.entity.UsersEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class UsersRepositoryImpl implements UsersCustomRepository {

    private final EntityManager entityManager;
    @Override
    public List<UsersEntity> findCustomQuery() {
        return Collections.emptyList();
    }
}
