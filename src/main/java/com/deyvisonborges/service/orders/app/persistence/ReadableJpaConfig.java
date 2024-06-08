// package com.deyvisonborges.service.orders.app.persistence;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.transaction.PlatformTransactionManager;

// import javax.sql.DataSource;

// @Configuration
// @EnableJpaRepositories(
//   basePackages = "com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read", 
//   entityManagerFactoryRef = "readableEntityManagerFactory", 
//   transactionManagerRef = "readableTransactionManager"
// )
// public class ReadableJpaConfig {

//   @Bean(name = "readableDataSource")
//   public DataSource readableDataSource() {
//     return DataSourceBuilder.create()
//       .url("jdbc:h2:mem:orders_readable;MODE=MYSQL;DATABASE_TO_LOWER=TRUE")
//       .driverClassName("org.h2.Driver")
//       .build();
//   }

//   @Bean(name = "readableEntityManagerFactory")
//   public LocalContainerEntityManagerFactoryBean readableEntityManagerFactory(
//     EntityManagerFactoryBuilder builder,
//     @Qualifier("readableDataSource") DataSource dataSource,
//     JpaProperties jpaProperties
//   ) {

//     return builder
//       .dataSource(dataSource)
//       .packages("com.deyvisonborges.service.orders.app.api.module.management.order.persistence")
//       .persistenceUnit("readable")
//       .properties(jpaProperties.getProperties())
//       .build();
//   }

//   @Bean(name = "readableTransactionManager")
//   public PlatformTransactionManager readableTransactionManager(
//       @Qualifier("readableEntityManagerFactory") LocalContainerEntityManagerFactoryBean readableEntityManagerFactory) {
//     return new JpaTransactionManager(readableEntityManagerFactory.getObject());
//   }
// }
