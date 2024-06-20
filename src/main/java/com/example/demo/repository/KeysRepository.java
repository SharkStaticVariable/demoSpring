package com.example.demo.repository;

import com.example.demo.entity.KeysEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeysRepository extends JpaRepository<KeysEntity, Integer> {
}
