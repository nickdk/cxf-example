package com.xti.nickdk;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.xti.nickdk.repositories")
public class PersistenceContext{
	private String dialect = "org.hibernate.dialect.HSQLDialect";
	@Bean
	public DataSource dataSource() {
		BoneCPDataSource ds = new BoneCPDataSource();

	    String username = "sa";
	    String dbUrl = "jdbc:hsqldb:mem:aname";
	    String password = "";
	    
	    String envDbUrl = System.getenv("DATABASE_URL");
		if(envDbUrl != null) {
			URI dbUri;
			try {
				dbUri = new URI(envDbUrl);
				username = dbUri.getUserInfo().split(":")[0];
				password = dbUri.getUserInfo().split(":")[1];
				dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
				dialect = "org.hibernate.dialect.PostgreSQLDialect";
			} catch (URISyntaxException e) {
				throw new IllegalStateException(e);
			}
		}
		
	 	ds.setJdbcUrl(dbUrl);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setMinConnectionsPerPartition(20);
		ds.setMaxConnectionsPerPartition(20);
		return ds;
	}
 
   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(dataSource());
      em.setPackagesToScan(new String[] { "com.xti.nickdk.entities" });
 
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      em.setJpaProperties(additionalProperties());
 
      return em;
   }
 
   @Bean
   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(emf);
 
      return transactionManager;
   }
 
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
   }
 
   Properties additionalProperties() {
      Properties properties = new Properties();
      properties.setProperty("hibernate.hbm2ddl.auto", "update");
      properties.setProperty("hibernate.dialect", dialect);
      return properties;
   }
}