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
        return "redirect:/login";
    }
}
