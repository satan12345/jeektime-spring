package com.able.duriddeom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void selectForUpdate() {
        Long aLong = jdbcTemplate.queryForObject("select id from foo where id = 1 for update", Long.class);
        try {
            System.out.println(Thread.currentThread().getName()+"查询到的结果为:"+aLong);

            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
    }
}
