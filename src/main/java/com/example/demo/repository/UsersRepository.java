package com.example.demo.repository;

import com.example.demo.entity.UsersEntity;
import com.example.demo.projection.UsersNameView;
import com.example.demo.projection.UsersNativeView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity,Integer>, UsersCustomRepository {

    Optional<UsersEntity> findByFirstNameContaining(String firstName);

    @Query("select u from UsersEntity u where u.firstName = :name and u.age = :age")
    List<UsersEntity> findAllByFirstNameAndAge(@Param("name") String firstName, @Param("age") Integer age);

    List<UsersNameView> findAllByAgeGreaterThan(Integer age);
    @Query(value = "select u.id as id, u.first_name || u.last_name as fullName from users u where u.age > :age", nativeQuery = true)
    List<UsersNativeView> findAllByAgeGreaterThanNative(@Param("age")Integer age);

}