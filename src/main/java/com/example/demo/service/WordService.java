package com.example.demo.service;

import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.UsersRepository;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class WordService {
    @Autowired
    private UsersRepository usersRepository;
    public byte[] createWord() {
        try (XWPFDocument document = new XWPFDocument();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            List<UsersEntity> users = usersRepository.findAll();

            // Добавление заголовка
            XWPFParagraph title = document.createParagraph();
            title.createRun().setText("Список пользователей");

            // Добавление данных пользователей
            for (UsersEntity user : users) {
                XWPFParagraph paragraph = document.createParagraph();
                paragraph.createRun().setText("ID: " + user.getId() + ", Логин: " + user.getUsername()
                        + ", Возраст: " + user.getAge() +", Фамилия: "+user.getLastName()+", Имя: " +
                        ""+user.getFirstName()+", Отчество: "+user.getMiddleName()+", Номер телефона: "
                        +user.getPhoneNumber()+"," +
                        " Адресс: "+user.getAddress()+", Эл. почта:"+user.getEmail());
            }
            document.write(baos);
            return baos.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Failed to create Word document", e);
        }
    }
}