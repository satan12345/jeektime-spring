package com.able.mulitdatasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class})
@Slf4j
public class MulitDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MulitDatasourceApplication.class, args);
    }

    @Bean
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDataSourceProperties(){
        return new DataSourceProperties();
    }
    @Bean
    public DataSource fooDataSource(){
        DataSourceProperties dataSourceProperties=fooDataSourceProperties();
        log.info("foo DataSource:{}",dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
    @Bean
    public PlatformTransactionManager fooTxManager(){
        return new DataSourceTransactionManager(fooDataSource());
    }
    @Bean
    @ConfigurationProperties("bar.datasource")
    public DataSourceProperties barDataSourceProperties(){
        return new DataSourceProperties();
    }
    @Bean
    public DataSource barDataSource(){
        DataSourceProperties dataSourceProperties=barDataSourceProperties();
        log.info("bar DataSource:{}",dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
    @Bean
    public PlatformTransactionManager barTxManager(){
        return new DataSourceTransactionManager(fooDataSource());
    }



}
