package com.example.demo.mapper;

import com.example.demo.dto.AccountsDTO;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.AccountsEntity;
import com.example.demo.entity.RolesEntity;
import com.example.demo.entity.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<UsersEntity, UserDto> {

    private final AccountsReadMapper accountsReadMapper;

    @Override
    public UserDto map(UsersEntity object) {

        return new UserDto(
                object.getUsername(),
                object.getFirstName(),
                object.getLastName(),
                object.getAge(),
                object.getPhoneNumber(),
                object.getAddress(),
                object.getMiddleName(),
                object.getEmail(),
                object.getPassword(),
                object.getRoles()
        );
    }
}