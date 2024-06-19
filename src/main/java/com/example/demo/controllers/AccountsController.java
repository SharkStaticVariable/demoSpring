package com.example.demo.controllers;


import com.example.demo.entity.AccountsEntity;
import com.example.demo.service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v2/apps")
public class AccountsController {
    private final AccountsService accountsService;


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/api/accounts")
    public ResponseEntity<List<AccountsEntity>> readAll(){
        return new ResponseEntity<>(accountsService.readAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
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