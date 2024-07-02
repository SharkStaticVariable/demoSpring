package com.example.demo.controllers;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.AccountsEntity;
import com.example.demo.entity.RolesEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.AccountsService;
import com.example.demo.service.EncryptDecryptService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
//@EnableMethodSecurity
public class UsersController {

    private final UserService userService;
    EncryptDecryptService encryptDecryptService;
    private final UserMapper userMapper;
    @Autowired
    private AccountsService accountService;


    @PutMapping("/api/user/put/{id}")
    public ResponseEntity<UserDto>  update(@PathVariable("id") Integer id, @RequestBody UserDto userDto){
        Optional<UserDto> updatedUser = userService.update(id, userDto);
        return userService.update(id, userDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
//    @PutMapping("/api/user/put/{id}")
//    public ResponseEntity<UserDto> update(@PathVariable("id") Integer id, @ModelAttribute UserDto userDto) {
//        Optional<UserDto> updatedUser = userService.update(id, userDto);
//        return updatedUser.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

//    @PostMapping
//    public String listUsers(Model model, @ModelAttribute UserDto userDto) {
//        List<UsersEntity> all = userService.findAll();
//        model.addAttribute("users", all);
//        return "admin";
//    }

//    @GetMapping("/admin")
//    public String adminPage(Model model) {
//        List<UsersEntity> allUsers = userService.findAll();
//        model.addAttribute("users", allUsers);
//        return "admin";
//    }

    @PostMapping("/api/save/users")
    public String create(Model model, @ModelAttribute UserDto userDto) {
        userDto.setRoles(RolesEntity.USER);
        UsersEntity usersEntity = userMapper.toUserEntity(userDto);

        String encryptedPassword = encryptDecryptService.encryptMessage(usersEntity.getPassword());
        usersEntity.setPassword(encryptedPassword);

        userService.save(usersEntity);
        List<UsersEntity> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);
        return "redirect:/login";
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UsersEntity>> findAll() {
        List<UsersEntity> users = userService.findAll();
//        users.forEach(user -> {
//            String decryptedPassword = encryptDecryptService.decryptMessage(user.getPassword());
//            user.setPassword(decryptedPassword);
//        });
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public String getRoles(Model model) {
        model.addAttribute("roles", Arrays.asList(RolesEntity.values()));
        return "roles";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute UserDto userDto) {

        UserDto build = UserDto.builder()
                .password("FASFA")
                .age(12)
                .email("123@gmail.com")
                .roles(RolesEntity.USER)
                .address("fasfa")
                .middleName("sfasf")
                .firstName("fafaf")
                .lastName("fasfa")
                .username("afsa")
                .phoneNumber("SAFSFAFA")
                .build();
        model.addAttribute("user", build);
        model.addAttribute("roles", RolesEntity.values());
        return "user/registration";
    }


//    @DeleteMapping("/api/user/delete/{id}")
//    public String delete(@PathVariable Integer id){
//      if(!userService.delete(id)){
//          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//      }
//      return "redirect:/user";
//    }
@DeleteMapping("/api/user/delete/{id}")
public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
    try {
        boolean isDeleted = userService.delete(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    } catch (Exception e) {
        System.err.println("Error deleting user with ID " + id + ": " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id, Model model){
//        return userService.findById(id)
//                .map(user->{
//                    model.addAttribute("user", user);
//                    model.addAttribute("roles", RolesEntity.values());
//                    return "licabinet";
//                })
//                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

//    @PostMapping("/create")
//    public String create(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes){
//        if(true){
//            redirectAttributes.addFlashAttribute("user", userDto);
//            return "redirect:/users/registration";
//        }
//       UserDto dto =  userService.create(userDto);
//        return "redirect:/users/" + dto.getUsername();
//    }
    //    @GetMapping("/api/users")
//    public String findAll(Model model){
//        model.addAttribute("users", userService.findAll());
//        return "users";
//    }

//    @PostMapping("/api/save/users")
//    public ResponseEntity<UsersEntity> create(@RequestBody UsersEntity usersEntity) {
//        String encryptedPassword = encryptDecryptService.encryptMessage(usersEntity.getPassword());
//        usersEntity.setPassword(encryptedPassword);
//        return new ResponseEntity<>(userService.save(usersEntity), HttpStatus.OK);
//    }

//    @PostMapping("/create")
//    public String create(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes){
//        if(true){
//            redirectAttributes.addFlashAttribute("user", userDto);
//            return "redirect:/users/registration";
//        }
//       UserDto dto =  userService.create(userDto);
//        return "redirect:/users/" + dto.getUsername();
//    }

}