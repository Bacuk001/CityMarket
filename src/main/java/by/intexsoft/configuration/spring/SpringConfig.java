package by.intexsoft.configuration.spring;

import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration class for spring. Configure the controller working with the
 * database. Manager for working with the database. Configures a class for
 * transaction management
 * 
 * @see {@link LocalContainerEntityManagerFactoryBean}, {@link List
 *      DriverManagerDataSource}, {@link JpaTransactionManager}
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("by.intexsoft.repository")
@PropertySource(value = { "classpath:resources/application.properties" })
public class SpringConfig {
	@Autowired
	private Environment environment;
	private static final UtilSpringConfig UTIL = UtilSpringConfig.singleton();

	/**
	 * Configure a persistence unit {@link LocalContainerEntityManagerFactoryBean}
	 * to interact with a database that allows you to participate in global
	 * transactions.
	 * 
	 * @see {@link LocalContainerEntityManagerFactoryBean},
	 *      {@link HibernateJpaVendorAdapter}.
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPackagesToScan(new String[] { UTIL.MODELS });
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.MYSQL);
		vendorAdapter.setShowSql(true);
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setJpaProperties(jpaProperties());
		return factory;
	}

	/**
	 * Configuring database connection settings
	 * 
	 * @see {@link DataSource}
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource builder = new DriverManagerDataSource();
		builder.setDriverClassName(environment.getRequiredProperty(UTIL.DRIVER));
		builder.setUrl(environment.getRequiredProperty(UTIL.DATABASE));
		builder.setUsername(environment.getRequiredProperty(UTIL.USER_NAME));
		builder.setPassword(environment.getRequiredProperty(UTIL.PASSWORD));
		return builder;
	}

	/**
	 * Configuring the Transaction Manager.
	 * 
	 * @see {@link JpaTransactionManager}.
	 */
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	/**
	 * The parameters of interaction with the database.
	 */
	public Properties jpaProperties() {
		Properties result = new Properties();
		result.put(UTIL.PROPERTY_DIALECT, environment.getRequiredProperty(UTIL.PROPERTY_DIALECT));
		result.put(UTIL.PROPERTY_DRIVE_DATA_BASE, environment.getRequiredProperty(UTIL.PROPERTY_DRIVE_DATA_BASE));
		result.put(UTIL.PROPERTY_SHOW_SQL, environment.getRequiredProperty(UTIL.PROPERTY_SHOW_SQL));
		result.put(UTIL.PROPERTY_GENERATOR, environment.getRequiredProperty(UTIL.PROPERTY_GENERATOR));
		return result;
	}
}
