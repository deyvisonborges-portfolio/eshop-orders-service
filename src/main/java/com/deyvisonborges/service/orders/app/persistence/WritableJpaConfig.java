package com.deyvisonborges.service.orders.app.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
  basePackages = "com.deyvisonborges.service.orders.app.api.module.management.order.persistence", 
  entityManagerFactoryRef = "writableEntityManagerFactory", 
  transactionManagerRef = "writableTransactionManager"
)
public class WritableJpaConfig {

  @Primary
  @Bean(name = "writableDataSource")
  public DataSource writableDataSource() {
    return DataSourceBuilder.create()
      // .url("jdbc:postgresql://${postgres.host}:${postgres.port}/${postgres.database}")
      // .username("${postgres.username}")
      // .password("${postgres.password}")
      // .driverClassName("org.postgresql.Driver")
      // .build();
      .url("jdbc:h2:mem:orders_writable;MODE=MYSQL;DATABASE_TO_LOWER=TRUE")
      .driverClassName("org.h2.Driver")
      .build();
  }

  @Primary
  @Bean(name = "writableEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean writableEntityManagerFactory(
      EntityManagerFactoryBuilder builder,
      @Qualifier("writableDataSource") DataSource dataSource,
      JpaProperties jpaProperties) {

    return builder
      .dataSource(dataSource)
      .packages("com.deyvisonborges.service.orders.app.api.module.management.order.persistence")
      .persistenceUnit("writable")
      .properties(jpaProperties.getProperties())
      .build();
  }

  @Primary
  @Bean(name = "writableTransactionManager")
  public PlatformTransactionManager writableTransactionManager(
      @Qualifier("writableEntityManagerFactory") LocalContainerEntityManagerFactoryBean writableEntityManagerFactory) {

    return new JpaTransactionManager(writableEntityManagerFactory.getObject());
  }
}
