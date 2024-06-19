package com.example.demo.controllers;

import com.example.demo.entity.AccountsEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.AccountsService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/apps")
public class UsersController {

    private final UserService userService;

    @PostMapping("/new-user")
    public String addUser(@RequestBody UsersEntity usersEntity){
    userService.addUser(usersEntity);
    return "success";
    }


    @GetMapping("/api/users")
    public ResponseEntity<List<UsersEntity>> readAll(){
        return new ResponseEntity<>(userService.readAll(), HttpStatus.OK);
    }

    @PutMapping("/api/user/put")
    public ResponseEntity<UsersEntity> update(@RequestBody UsersEntity usersEntity){
        return new ResponseEntity<>(userService.update(usersEntity), HttpStatus.OK);
    }

    @DeleteMapping("/api/user/delete/{id}")
    public HttpStatus delete(@PathVariable Integer id){
        userService.delete(id);
        return HttpStatus.OK;
    }
}
