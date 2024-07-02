package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.RolesEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.mapper.UserCreateEditMapper;
import com.example.demo.mapper.UserReadMapper;
import com.example.demo.repository.KeysRepository;
import com.example.demo.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private UsersRepository usersRepository;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserReadMapper userReadMapper;
    public EncryptDecryptService encryptDecryptService;
    public KeysRepository keysRepository;



    public List<UsersEntity> findAll(){
        return usersRepository.findAll();

    }

    public Optional<UsersEntity> findById(Integer id) {
//        return usersRepository.findById(id);
        Optional<UsersEntity> userOptional = usersRepository.findById(id);
        userOptional.ifPresent(user -> {
            String decryptedPassword = encryptDecryptService.decryptMessage(user.getPassword());
            user.setPassword(decryptedPassword);
        });
        return userOptional;
    }

    @Transactional
    public UsersEntity save(UsersEntity usersEntity) {
        return usersRepository.save(UsersEntity.builder()
                .email(usersEntity.getEmail())
                .password(usersEntity.getPassword())
                .phoneNumber(usersEntity.getPhoneNumber())
                        .age(usersEntity.getAge())
                        .middleName(usersEntity.getMiddleName())
                        .address(usersEntity.getAddress())
                        .firstName(usersEntity.getFirstName())
                        .lastName(usersEntity.getLastName())
                        .username(usersEntity.getUsername())
                        .roles(usersEntity.getRoles())
                .build());
    }

@Transactional
public Optional<UserDto> update(Integer id, UserDto userDto) {
    return usersRepository.findById(id)
            .map(entity -> {
                // Update user entity with new data from userDto
                entity.setEmail(userDto.getEmail());
                entity.setPhoneNumber(userDto.getPhoneNumber());
                entity.setAge(userDto.getAge());
                entity.setMiddleName(userDto.getMiddleName());
                entity.setAddress(userDto.getAddress());
                entity.setFirstName(userDto.getFirstName());
                entity.setLastName(userDto.getLastName());
                entity.setUsername(userDto.getUsername());

                // Encrypt password before saving to DB
                String encryptedPassword = encryptDecryptService.encryptMessage(userDto.getPassword());
                entity.setPassword(encryptedPassword);

                // Save and return updated userDto
                UsersEntity updatedEntity = usersRepository.save(entity);
                return userReadMapper.map(updatedEntity);
            });
}

@Transactional
public boolean delete(Integer id) {
    try {
        usersRepository.deleteById(id);
        return true;
    } catch (Exception e) {
        // Логирование ошибок удаления
        System.err.println("Error deleting user with ID " + id + ": " + e.getMessage());
        return false;
    }
}
    @Transactional
    public boolean updateUserRole(Integer id, String newRole) {
        log.info("Attempting to update role for user with ID: {}", id);

        Optional<UsersEntity> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {

            UsersEntity user = optionalUser.get();
            log.info("User found: {}", user);

            try {
                RolesEntity roleEntity = RolesEntity.valueOf(newRole.toUpperCase());
                log.info("New role entity: {}", roleEntity);
// Преобразование строки к enum
                user.setRoles(roleEntity);  // Обновление роли
                log.info("Role set to {} for user with ID: {}", roleEntity, id);

                usersRepository.save(user);  // Сохранение изменений в БД
                log.info("User saved: {}", user);

                return true;
            } catch (IllegalArgumentException e) {
                log.error("Invalid role value: {}", newRole, e);
                return false;
            } catch (Exception e) {
                log.error("Error saving user with ID: {}", id, e);
                return false;
            }
        }

        log.warn("User with ID: {} not found", id);
        return false;
    }


    public UsersEntity getUserById(Integer userId) {
        return usersRepository.findById(userId).orElse(null);
    }
    public Integer getUserIdByUsername(String username) {
        return usersRepository.findByUsername(username)
                .map(UsersEntity::getId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
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