package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
//    @PostMapping("/login")
//    public String login(Model model, @ModelAttribute LoginDto loginDto) {
//        return "redirect:/login";
//    }
}
