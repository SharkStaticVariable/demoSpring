package com.example.demo.controllers;

import com.example.demo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/word")
public class WordController {
    @Autowired
    private WordService wordService;

    @GetMapping("/create-word")
    public ResponseEntity<byte[]> createWord() {
        byte[] word = wordService.createWord();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=document.docx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(word);
    }
}
