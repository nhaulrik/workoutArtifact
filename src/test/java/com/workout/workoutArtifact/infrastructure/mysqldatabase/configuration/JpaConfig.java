package com.workout.workoutArtifact.infrastructure.mysqldatabase.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.workout.workoutArtifact.infrastructure.mysqldatabase")
@TestPropertySource("application.properties")
@EnableTransactionManagement
public class JpaConfig {

  @Autowired
  private Environment environment;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
    dataSource.setUrl(environment.getProperty("jdbc.url"));
    dataSource.setUsername(environment.getProperty("jdbc.user"));
    dataSource.setPassword(environment.getProperty("jdbc.pass"));

    return dataSource;
  }

  @Bean
  public EntityManagerFactory entityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(Boolean.TRUE);
    vendorAdapter.setShowSql(Boolean.TRUE);
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.workout.workoutArtifact.infrastructure.mysqldatabase");
    factory.setDataSource(dataSource());
    factory.afterPropertiesSet();
    factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
    return factory.getObject();
  }

  @Bean(name = "transactionManager")
  public PlatformTransactionManager dbTransactionManager() {
    JpaTransactionManager transactionManager
        = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
        entityManagerFactory());
    return transactionManager;
  }

  // configure additional Hibernate Properties
}