package com.able.transactionproagationdemo;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

@SpringBootApplication
@Slf4j
public class TransactionProagationDemoApplication implements CommandLineRunner {

    @Autowired
    private FooService fooService;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
        SpringApplication.run(TransactionProagationDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            fooService.invokeInsertThenRollback();
        } catch (Exception e) {

        }
        log.info("AAA {}", jdbcTemplate.queryForObject("select count(*) from foo where bar='AAA'", Long.class));
        log.info("BBB {}", jdbcTemplate.queryForObject("select count(*) from foo where bar='BBB'", Long.class));

    }

}
