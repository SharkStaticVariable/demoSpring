package com.example.demo.projection;


import org.springframework.beans.factory.annotation.Value;

public interface UsersNameView {

    String getLastName();
    @Value("#{target.id + ' ' + target.firstName}")
    String getFirstName();
}
