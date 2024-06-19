package com.example.demo.service;

import com.example.demo.config.MyUserDetails;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
//public class MyUserDetailsService  implements UserDetailsService {
//    @Autowired
//    private UsersRepository usersRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UsersEntity> usersEntity = usersRepository.findByName(username);
//        return (UserDetails) usersEntity.map(MyUserDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
//    }
//}
