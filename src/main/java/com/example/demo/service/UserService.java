package com.example.demo.service;

import com.example.demo.entity.AccountsEntity;
import com.example.demo.entity.RolesEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.AccountsRepository;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
private UsersRepository usersRepository;

    public void addUser(UsersEntity usersEntity) {
//        usersEntity.setPassword(passwordEncoder.encode(usersEntity.getPassword()));
        usersRepository.save(usersEntity);
    }
//    public UsersEntity save(UsersEntity usersEntity) {
//        return usersRepository.save(UsersEntity.builder()
//                .age(usersEntity.getAge())
//                .firstName(usersEntity.getFirstName())
//                .lastName(usersEntity.getLastName())
//                        .email(usersEntity.getEmail())
//                        .phoneNumber(usersEntity.getPhoneNumber())
//                        .address(usersEntity.getAddress())
//                        .documentNumber(usersEntity.getDocumentNumber())
//                        .password(usersEntity.getPassword())
//                .build());
//    }
//    @Autowired
//    private UsersRepository usersRepository;
//    @Autowired
//    private RolesRepository rolesRepository;
//

//    @Override
//    public void save(UsersEntity usersEntity) {
//        Set<RolesEntity> rolesEntities = new HashSet<>();
//        rolesEntities.add(rolesRepository.getOne(1));
//        usersEntity.setRoles(rolesEntities);
//        usersRepository.save(usersEntity);
//    }

//

    public List<UsersEntity> readAll() {
        return usersRepository.findAll();
    }

    public UsersEntity update(UsersEntity usersEntity) {
        return usersRepository.save(usersEntity);
    }

    public void delete(Integer id) {
        usersRepository.deleteById(id);
    }

}
