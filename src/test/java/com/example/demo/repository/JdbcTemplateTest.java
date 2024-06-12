package com.example.demo.repository;

import com.example.demo.IntegrationBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

class JdbcTemplateTest extends IntegrationBaseTest {
    private static final String Insert_SQL="insert into accounts (balance, isactive, islocked) values (?,?,?);";

    @Autowired
    private JdbcOperations jdbcOperations;

    @Test
    void testInsert(){
        int result = jdbcOperations.update(Insert_SQL, "1000");
        Assertions.assertEquals(1, result);
    }
}
