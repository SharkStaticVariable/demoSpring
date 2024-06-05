package com.example.demo.config;

import com.example.demo.conditional.FirstConditional;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
public class ApplicationConfiguration {

    @PostConstruct
    public void init(){
    log.warn("app is loaded!");
    }
}
