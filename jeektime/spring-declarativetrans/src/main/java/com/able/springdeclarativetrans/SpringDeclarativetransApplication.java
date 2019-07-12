package com.able.springdeclarativetrans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
public class SpringDeclarativetransApplication implements CommandLineRunner {

    @Resource
    FooService fooService;
    @Resource
    JdbcTemplate jdbcTemplate;
    public static void main(String[] args) {
        SpringApplication.run(SpringDeclarativetransApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        fooService.insertRecord();

        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='AAA'", Long.class);
        log.info("AAA={}",count);

        try {
            fooService.insertThenRollback();
        } catch (RollbackException e) {
           // e.printStackTrace();
        }

        count=jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'",Long.class);
        log.info("BBB={}",count);

//        try {
//            fooService.invokeInsertThenRollback();
//        } catch (RollbackException e) {
//            //e.printStackTrace();
//        }
//        count=jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'",Long.class);
//        log.info("BBB={}",count);
        try {
            fooService.invokeInsertThenRollbackByAopContext();
        } catch (RollbackException e) {
            //e.printStackTrace();
        }
        count=jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'",Long.class);
        log.info("BBB={}",count);

    }
}
