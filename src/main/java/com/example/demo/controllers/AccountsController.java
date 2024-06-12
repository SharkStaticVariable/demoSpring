package com.example.demo.controllers;

import com.example.demo.dto.AccountsDTO;
import com.example.demo.entity.AccountsEntity;
import com.example.demo.service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountsController {
    private final AccountsService accountsService;

    @GetMapping("/api/accounts")
    public ResponseEntity<List<AccountsEntity>> readAll(){
        return new ResponseEntity<>(accountsService.readAll(), HttpStatus.OK);
    }



    @PostMapping("/api/save")
    /*
    public ResponseEntity<AccountsEntity> create(@RequestBody AccountsDTO dto){
        return new ResponseEntity<>(accountsService.create(dto), HttpStatus.OK);
    }*/
    public ResponseEntity<AccountsEntity> save(@RequestBody AccountsEntity accountsEntity) {
        return new ResponseEntity<>(accountsService.save(accountsEntity), HttpStatus.OK);
    }


    @PutMapping("/api/put")
    public ResponseEntity<AccountsEntity> update(@RequestBody AccountsEntity accountsEntity){
        return new ResponseEntity<>(accountsService.update(accountsEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Integer id){
    accountsService.delete(id);
    return HttpStatus.OK;
    }

}