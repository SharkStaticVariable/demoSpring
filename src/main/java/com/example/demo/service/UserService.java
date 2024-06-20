package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.AccountsEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.mapper.UserCreateEditMapper;
import com.example.demo.mapper.UserReadMapper;
import com.example.demo.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private UsersRepository usersRepository;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserReadMapper userReadMapper;
    EncryptDecryptService encryptDecryptService;

    public List<UsersEntity> findAll(){
        return usersRepository.findAll();

    }

    public Optional<UserDto> findById(Integer id){
        return usersRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UsersEntity save(UsersEntity usersEntity) {
        return usersRepository.save(UsersEntity.builder()
                .email(usersEntity.getEmail())
                .password(usersEntity.getPassword())
                .phoneNumber(usersEntity.getPhoneNumber())
                        .age(usersEntity.getAge())
                        .documentNumber(usersEntity.getDocumentNumber())
                        .address(usersEntity.getAddress())
                        .firstName(usersEntity.getFirstName())
                        .lastName(usersEntity.getLastName())
                        .username(usersEntity.getUsername())
                        .roles(usersEntity.getRoles())
                        .accounts(usersEntity.getAccounts())
                .build());
    }

    @Transactional
    public Optional<UserDto> update(Integer id, UserDto userDto) {
        return usersRepository.findById(id)
                .map(entity->userCreateEditMapper.map(userDto, entity))
                .map(usersRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return usersRepository.findById(id)
                .map(entity->{
                    usersRepository.delete(entity);
                    usersRepository.flush();
                    return true;
                })
                .orElse(false);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return usersRepository.findByUsername(username)
//                .map(user -> new org.springframework.security.core.userdetails.User(
//                        user.getUsername(),
//                        user.getPassword(),
//                        Collections.singleton(user.getRoles())
//                ))
//                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username))
//                ;
//    }
}
