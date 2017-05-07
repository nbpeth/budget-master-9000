package com.homebudget;

import com.homebudget.service.ExpenseService;
import com.homebudget.service.RecurringExpenseService;
import com.homebudget.service.StatisticsService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        //mysql://b526f03d0cf043:b772b096@us-cdbr-iron-east-03.cleardb.net/heroku_7be06830f6ea887?reconnect=true

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(getDbUri());
        dataSource.setUsername("nbpeth");
        return dataSource;
    }

    private String getDbUri(){
        String prodUrl = System.getenv("CLEARDB_DATABASE_URL");
        return prodUrl != null ? prodUrl : "jdbc:mysql://127.0.0.1:3306/home_budget?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=CST";
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(dataSource());
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("spring.jpa.database-platform", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.put("spring.jpa.show-sql", "true");
        hibernateProperties.put("spring.jpa.hibernate.ddl-auto", "update");
        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
    }

    @Bean
    public ExpenseService expenseService(){
        return new ExpenseService();
    }

    @Bean
    public RecurringExpenseService recurringExpenseService(){
        return new RecurringExpenseService();
    }

    @Bean
    public StatisticsService statisticsService(){
        return new StatisticsService();
    }
}
