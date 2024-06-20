package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UsersEntity toUserEntity(UserDto userDto);
}
