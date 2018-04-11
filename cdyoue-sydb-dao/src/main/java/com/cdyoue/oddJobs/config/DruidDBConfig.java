package com.cdyoue.oddJobs.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by cdyoue on 2017/1/23.
 */
@Configuration
@EnableTransactionManagement // 开启注解事务
@Order(4)
public class DruidDBConfig {
    private Logger logger = Logger.getLogger(getClass());

    @Value("${spring.datasource.jdbc-url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Value("${spring.datasource-icnpp.jdbc-url}")
    private String icnppDbUrl;
    @Value("${spring.datasource-icnpp.username}")
    private String icnppUsername;
    @Value("${spring.datasource-icnpp.password}")
    private String icnppPassword;

    @Value("${spring.datasource-icnpp.jdbc-url}")
    private String syDbUrl;
    @Value("${spring.datasource-icnpp.username}")
    private String syUsername;
    @Value("${spring.datasource-icnpp.password}")
    private String syPassword;

    @Bean(name="primaryDataSource")
    @Qualifier("primaryDataSource")
    @Primary
    public DataSource lgsqDataSource() throws SQLException {

        DruidDataSource datasource = initSouce();
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setFilters(filters);
        return datasource;
    }

    @Bean(name="icnppDataSource")
    @Qualifier("icnppDataSource")
    public DataSource icnppDataSource() throws SQLException {

        DruidDataSource datasource = initSouce();
        datasource.setUrl(icnppDbUrl);
        datasource.setUsername(syUsername);
        datasource.setPassword(syPassword);
        datasource.setFilters(filters);
        return datasource;
    }

    @Bean(name="syDataSource")
    @Qualifier("syDataSource")
    public DataSource syDataSource() throws SQLException {
        DruidDataSource datasource = initSouce();
        datasource.setUrl(syDbUrl);
        datasource.setUsername(icnppUsername);
        datasource.setPassword(icnppPassword);
        datasource.setFilters(filters);
        return datasource;
    }




    private DruidDataSource initSouce() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setDriverClassName(driverClassName);
        // configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }


}
