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
@RequestMapping("/acc")
public class AccountsController {
    private final AccountsService accountsService;
    private final UserService userService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<AccountsEntity> createAccount(@PathVariable Integer userId) {
        // Получаем пользователя по его ID
        UsersEntity user = userService.getUserById(userId);

        // Проверяем, что пользователь существует
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Создаем счет для пользователя
        AccountsEntity createdAccount = accountsService.createAccountForUser(user);

        return ResponseEntity.ok(createdAccount);
    }



    @GetMapping("/api/accounts")
    public ResponseEntity<List<AccountsEntity>> readAll(){
        return new ResponseEntity<>(accountsService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the unprotected page";
    }

    @PostMapping("/api/save")
    public ResponseEntity<AccountsEntity> save(@RequestBody AccountsEntity accountsEntity) {
        return new ResponseEntity<>(accountsService.save(accountsEntity), HttpStatus.OK);
    }

    @PutMapping("/api/put")
    public ResponseEntity<AccountsEntity> update(@RequestBody AccountsEntity accountsEntity){
        return new ResponseEntity<>(accountsService.update(accountsEntity), HttpStatus.OK);
    }

    @DeleteMapping("/api/delete/{id}")
    public HttpStatus delete(@PathVariable Integer id){
    accountsService.delete(id);
    return HttpStatus.OK;
    }
}