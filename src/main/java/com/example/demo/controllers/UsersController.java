package com.example.demo.controllers;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.AccountsEntity;
import com.example.demo.entity.RolesEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.EncryptDecryptService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
//@EnableMethodSecurity
public class UsersController {

    private final UserService userService;
    EncryptDecryptService encryptDecryptService;
//    @PostMapping("/create")
//    public String create(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes){
//        if(true){
//            redirectAttributes.addFlashAttribute("user", userDto);
//            return "redirect:/users/registration";
//        }
//       UserDto dto =  userService.create(userDto);
//        return "redirect:/users/" + dto.getUsername();
//    }

    @PostMapping("/api/save/users")
    public ResponseEntity<UsersEntity> create(@RequestBody UsersEntity usersEntity) {
        String encryptedPassword = encryptDecryptService.encryptMessage(usersEntity.getPassword());
        usersEntity.setPassword(encryptedPassword);
        return new ResponseEntity<>(userService.save(usersEntity), HttpStatus.OK);
    }

//    @GetMapping("/api/users")
//    public String findAll(Model model){
//        model.addAttribute("users", userService.findAll());
//        return "users";
//    }


    @GetMapping("/api/users")
    public ResponseEntity<List<UsersEntity>> findAll() {
        List<UsersEntity> users = userService.findAll();
        users.forEach(user -> {
            String decryptedPassword = encryptDecryptService.decryptMessage(user.getPassword());
            user.setPassword(decryptedPassword);
        });
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public String findById(@PathVariable("id") Integer id, Model model){
        return userService.findById(id)
                .map(user->{
                    model.addAttribute("user", user);
                    model.addAttribute("roles", RolesEntity.values());
                    return "user";
                })
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute UserDto userDto) {
        model.addAttribute("user", userDto);
        model.addAttribute("roles", RolesEntity.values());
        return "user/registration";
    }

    @PutMapping("/api/user/put/{id}")
    public String  update(@PathVariable("id") Integer id, @ModelAttribute UserDto userDto){
        return userService.findById(id)
                .map(it->"redirect:/user/{id}")
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/api/user/delete/{id}")
    public String delete(@PathVariable Integer id){
      if(!userService.delete(id)){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
      return "redirect:/user";

    }
}