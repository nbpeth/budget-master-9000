package com.homebudget;

import com.homebudget.service.ExpenseService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories({"com.homebudget.repository"})
@EntityScan
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class Config {

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/");
//        dataSource.setUsername("nbpeth");
//        return dataSource;
//    }

    @Bean
    public ExpenseService expenseService(){
        return new ExpenseService();
    }

}
