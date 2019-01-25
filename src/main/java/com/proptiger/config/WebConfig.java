/*
 * WebConfig Class
 * Author : Sachin Chauhan
 * 
 *  This class is for set basic system Configurations 
 *  And for Connect with My database
 */



package com.proptiger.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.proptiger.service.Constants;

@Configuration
@EnableWebMvc
@EnableScheduling
@EnableAsync
@ComponentScan("com.proptiger")
@EnableJpaRepositories(basePackages= {"com.proptiger"})
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	   public EntityManagerFactory entityManagerFactory(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName(Constants.driverClassName);
	      dataSource.setUrl(Constants.DbHostName);
	      dataSource.setUsername(Constants.DbUserName);
	      dataSource.setPassword(Constants.DbPassword);
	      LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	      factory.setJpaVendorAdapter(createJPAAdapter());
	      factory.setDataSource(dataSource);
	      factory.setPersistenceProviderClass(HibernatePersistence.class);
	      factory.setPackagesToScan("com.proptiger");
	      factory.setJpaProperties(createJPAProperties());
	      factory.afterPropertiesSet();
	      return factory.getObject();
	   }

	   @Bean
	  public JpaTransactionManager transactionManager() throws Exception {
	      JpaTransactionManager transactionManager = new JpaTransactionManager();
	      transactionManager.setEntityManagerFactory(entityManagerFactory());

	      return transactionManager;
	  }

	   private Properties createJPAProperties() {
	      Properties properties = new Properties();
	      return properties;
	  }

	   private HibernateJpaVendorAdapter createJPAAdapter() {
	       HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	       vendorAdapter.setShowSql(true);
	       vendorAdapter.setGenerateDdl(true);
	       vendorAdapter.setDatabase(Database.MYSQL);

	      return vendorAdapter;
	  }

}
