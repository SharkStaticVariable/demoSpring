package com.example.demo.controllers;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.AccountsEntity;
import com.example.demo.entity.RolesEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/licabinet")
@Slf4j
public class LiCabinetController {

    @GetMapping
    public String licabinetPage() {
        log.info("Переход на страницу личного кабинета");
        return "user/licabinet";
    }
}