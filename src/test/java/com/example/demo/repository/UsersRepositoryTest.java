package com.example.demo.repository;

import com.example.demo.IntegrationBaseTest;
import com.example.demo.entity.UsersEntity;
import com.example.demo.projection.UsersNameView;
import com.example.demo.projection.UsersNativeView;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

class UsersRepositoryTest extends IntegrationBaseTest {
    private static final Integer Ivan_Id = 1;
    @Autowired
    private UsersRepository usersRepository;

    @Test
    void testFindById(){
    Optional<UsersEntity> user =usersRepository.findById(Ivan_Id);
    Assertions.assertTrue(user.isPresent());
    }

    @Test
    void testFindByFirstNameContaining(){
        Optional<UsersEntity> user =usersRepository.findByFirstNameContaining("Ivan");
        assertTrue(user.isPresent());
    }

    @Test
    void testFindAllByFirstNameAndAge(){
        List<UsersEntity> users = usersRepository.findAllByFirstNameAndAge("Ivan",24);
        assertThat(users, IsCollectionWithSize.hasSize(1));
    }
    
    @Test
    void testFindAllByAge(){
        List<UsersNameView> users = usersRepository.findAllByAgeGreaterThan(2);
        assertThat(users, hasSize(2));
    }

    @Test
    void testFindAllByAgeNative(){
        List<UsersNativeView> users = usersRepository.findAllByAgeGreaterThanNative(2);
        assertThat(users, hasSize(2));
    }
    
    @Test
    void testFindCustomQuery(){
        List<UsersEntity> users = usersRepository.findCustomQuery();
        assertThat(users, hasSize(0));
    }
}