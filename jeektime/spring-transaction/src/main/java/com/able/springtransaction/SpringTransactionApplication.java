package com.able.springtransaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

@SpringBootApplication
@Slf4j
public class SpringTransactionApplication implements CommandLineRunner {

    @Resource
    TransactionTemplate transactionTemplate;
    @Resource
    JdbcTemplate jdbcTemplate;
    public static void main(String[] args) {
        SpringApplication.run(SpringTransactionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("count before transaction:{}",getCount());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                jdbcTemplate.execute("insert into FOO(ID,BAR) values (1,'aaa')");
                log.info("count in transaction:{}",getCount());
                status.setRollbackOnly();
            }
        });
        log.info("count after transaction={}",getCount());
    }


    private long getCount(){
        return jdbcTemplate.queryForObject("select count(*) from FOO", Long.class);
    }
}
