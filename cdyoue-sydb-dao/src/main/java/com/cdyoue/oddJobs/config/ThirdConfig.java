package com.cdyoue.oddJobs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryThird",
        transactionManagerRef="transactionManagerThird",
        basePackages= {"com.cdyoue.oddJobs.dao.syData"})
public class ThirdConfig {

    @Autowired
    @Qualifier("syDataSource")
    private DataSource thirdDataSource;


    @Bean(name = "entityManagerThird")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryThird(builder).getObject().createEntityManager();
    }


    @Bean(name = "entityManagerFactoryThird")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryThird(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(thirdDataSource)
                .properties(getVendorProperties(thirdDataSource))
                .packages("com.cdyoue.oddJobs.entity.syData")
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }


    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManagerThird")
     PlatformTransactionManager transactionManagerThird(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryThird(builder).getObject());
    }

}