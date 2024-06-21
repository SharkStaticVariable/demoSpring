package com.example.demo.controllers;

import com.example.demo.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping("/in")
    public String login(Model model, @ModelAttribute LoginDto loginDto) {
        log.info("Данные для входа: {}", loginDto);

        if (userHasRole(loginDto.getUsername(), "ADMIN")) {
            return "redirect:/admin";
        } else {
            return "redirect:/licabinet";
        }
    }

    private boolean userHasRole(String username, String role) {
        // Здесь может быть логика проверки роли пользователя в вашей системе
        // В данном примере просто предполагаем, что пользователь с ролью ADMIN
        // имеет имя пользователя "admin"
        return "admin".equals(username) && "ADMIN".equals(role);
    }

}