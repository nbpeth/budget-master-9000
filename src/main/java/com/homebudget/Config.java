package com.homebudget;

import com.homebudget.service.ExpenseService;
import com.homebudget.service.StatisticsService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories({"com.homebudget.repository"})
@EntityScan
@EnableTransactionManagement
public class Config {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/home_budget?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUsername("nbpeth");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("spring.jpa.database-platform", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.put("spring.jpa.show-sql", "true");
        hibernateProperties.put("spring.jpa.hibernate.ddl-auto", "create");
        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
    }

    @Bean
    public ExpenseService expenseService(){
        return new ExpenseService();
    }

    @Bean
    public StatisticsService statisticsService(){
        return new StatisticsService();
    }
}
