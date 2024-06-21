package com.example.demo.controllers;

import com.example.demo.entity.RolesEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.UserService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return userService.findById(id)
                .map(user->{
                    model.addAttribute("user", user);
                    model.addAttribute("roles", RolesEntity.values());
                    return "user";
                })
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public String adminPage(Model model) {
        log.info("Переход на страницу админ-панели");
        List<UsersEntity> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);
        return "user/admin";
    }

}
