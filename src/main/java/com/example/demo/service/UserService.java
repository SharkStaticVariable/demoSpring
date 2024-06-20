package com.example.demo.service;

import com.example.demo.dto.UserDto;

import com.example.demo.mapper.UserCreateEditMapper;
import com.example.demo.mapper.UserReadMapper;
import com.example.demo.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private UsersRepository usersRepository;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserReadMapper userReadMapper;


    public List<UserDto> findAll(){
        return usersRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();

    }

    public Optional<UserDto> findById(Integer id){
        return usersRepository.findById(id)
                .map(userReadMapper::map);
    }
    @Transactional
    public UserDto create(UserDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(usersRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRoles())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username))
                ;
    }
}
