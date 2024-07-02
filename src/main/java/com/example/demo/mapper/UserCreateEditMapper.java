package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.AccountsEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserDto, UsersEntity>{


    private final AccountsRepository accountsRepository;

    public UsersEntity map(UserDto fromObject, UsersEntity toObject){
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public UsersEntity map(UserDto object) {
        UsersEntity usersEntity = new UsersEntity();

        copy(object, usersEntity);
        return null;
    }

    private void copy(UserDto object, UsersEntity usersEntity) {
        usersEntity.setUsername(object.getUsername());
        usersEntity.setPassword(object.getPassword());
        usersEntity.setEmail(object.getEmail());
        usersEntity.setAddress(object.getAddress());
        usersEntity.setMiddleName(object.getMiddleName());
        usersEntity.setFirstName(object.getFirstName());
        usersEntity.setLastName(object.getLastName());
        usersEntity.setPhoneNumber(object.getPhoneNumber());
        usersEntity.setRoles(object.getRoles());
    }

    private AccountsEntity getAccountsEntity(Integer accountsId) {
        return Optional.ofNullable(accountsId)
                .flatMap(accountsRepository::findById)
                .orElse(null);
    }
}
