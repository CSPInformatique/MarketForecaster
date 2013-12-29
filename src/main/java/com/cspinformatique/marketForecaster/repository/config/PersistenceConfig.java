package com.cspinformatique.marketForecaster.repository.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:persistence/hibernate.properties")
@EnableJpaRepositories(basePackages="com.cspinformatique.marketForecaster.repository")
public class PersistenceConfig {
	private static final String HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_HBM2DDL_IMPORT_FILES = "hibernate.hbm2ddl.import_files";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.showSql";
    private static final String HIBERNATE_PACKAGESTOSCAN = "hibernate.packagesToScan";
    
	@Autowired private DataSource datasource;
	
	@Resource private Environment env;
	
	public @Bean LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(datasource);
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
		entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(HIBERNATE_PACKAGESTOSCAN));
		entityManagerFactoryBean.setJpaProperties(hibProperties());

		return entityManagerFactoryBean;
	}
    	
    private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(HIBERNATE_DIALECT, env.getRequiredProperty(HIBERNATE_DIALECT));
		properties.put(HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(HIBERNATE_HBM2DDL_AUTO));
		properties.put(HIBERNATE_HBM2DDL_IMPORT_FILES, env.getRequiredProperty(HIBERNATE_HBM2DDL_IMPORT_FILES));
		properties.put(HIBERNATE_SHOW_SQL, env.getRequiredProperty(HIBERNATE_SHOW_SQL));
		return properties;	
	}
    
	public @Bean JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}
