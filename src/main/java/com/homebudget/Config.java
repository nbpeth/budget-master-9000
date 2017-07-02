package com.homebudget;

import com.homebudget.service.ExpenseService;
import com.homebudget.service.RecurringExpenseService;
import com.homebudget.service.StatisticsService;
import com.homebudget.service.authentication.LoginService;
import com.homebudget.service.authentication.TokenService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableJpaRepositories({"com.homebudget.repository"})
@EntityScan
@EnableTransactionManagement
public class Config {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //String prodUri = "jdbc:mysql://b526f03d0cf043:b772b096@us-cdbr-iron-east-03.cleardb.net/heroku_7be06830f6ea887?reconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=CST";
        String dbUri = System.getenv("CLEARDB_DATABASE_URL");
//        String username = "b526f03d0cf043";
//        String password = "b772b096";
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(dbUri);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/home_budget?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=CST");
//        dataSource.setUsername("nbpeth");
//
//        return dataSource;
//    }
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
    public LoginService loginService() {
        return new LoginService();
    }

    @Bean
    public TokenService tokenService(){
        return new TokenService();
    }

    @Bean
    public ExpenseService expenseService() {
        return new ExpenseService();
    }

    @Bean
    public RecurringExpenseService recurringExpenseService() {
        return new RecurringExpenseService();
    }

    @Bean
    public StatisticsService statisticsService() {
        return new StatisticsService();
    }

    @Bean
    public Map<String, String> environmentVariables(){
        Map<String, String> envVars = new HashMap<>();
        envVars.put("apiSecret", System.getenv("API_SECRET"));
        envVars.put("apiSubject", System.getenv("API_SUBJECT"));
        envVars.put("apiIssuer", System.getenv("API_ISSUER"));
        envVars.put("apiKey", System.getenv("BUDGETMASTER_API_KEY"));
        envVars.put("apiId", "budgetMaster");

        return envVars;
    }
}
