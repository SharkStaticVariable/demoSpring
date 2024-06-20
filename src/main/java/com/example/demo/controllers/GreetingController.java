package com.example.demo.controllers;

import com.example.demo.dto.UserReadDto;
import com.example.demo.entity.RolesEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("api/v1")
public class GreetingController {

    @ModelAttribute("roles")
    public List<RolesEntity> getRoles(){
        return Arrays.asList(RolesEntity.values());
    }

    @GetMapping("/hello")
    public String hello(Model model, UserReadDto userReadDto){
        model.addAttribute("user", userReadDto);
        return "hello";
    }
}
