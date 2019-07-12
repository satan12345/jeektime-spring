package com.able.springdeclarativetrans;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringDeclarativetransApplicationTests {

    @Autowired
    private FooService fooService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    public void contextLoads() {
    }
    @Test
    public void invokeInsertThenRollback() {
        try {
            fooService.invokeInsertThenRollback();
        } catch (Exception e) {
            log.info("BBB {}",
                    jdbcTemplate
                            .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
        }
    }
//    @Test
//    public void invokeInsertThenRollbackBySelfService() {
//        try {
//            fooService.invokeInsertThenRollbackBySelfService();
//        } catch (Exception e) {
//            log.info("BBB {}",
//                    jdbcTemplate
//                            .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
//        }
//    }
//    @Test
//    public void invokeInsertThenRollbackByAopContext() {
//        try {
//            fooService.invokeInsertThenRollbackByAopContext();
//        } catch (Exception e) {
//            log.info("BBB {}",
//                    jdbcTemplate
//                            .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
//        }
//    }
//    @Test
//    public void invokeInsertThenRollbackAddTransactional() {
//        try {
//            fooService.invokeInsertThenRollbackAddTransactional();
//        } catch (Exception e) {
//            log.info("BBB {}",
//                    jdbcTemplate
//                            .queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
//        }
//    }

}
