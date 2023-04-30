package group.megamarket.marketservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Конфигурационный класс для подключения к базе данных и настройки управления транзакциями и JPA
 *
 * @author Eldar
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "group.megamarket.marketservice")
@PropertySource(value = "classpath:application.properties")
public class PersistenceConfig {
    private final Environment environment;

    public PersistenceConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * Создает и настраивает объект DriverManagerDataSource
     *
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    /**
     * Создает и настраивает объект LocalContainerEntityManagerFactoryBean, который представляет собой фабрику сущностей JPA
     *
     * @param dataSource
     * @return объект LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        final var emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("group.megamarket.marketservice");

        final var vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(hibernateProperties());

        return emf;
    }

    /**
     * Создает и настраивает объект JpaTransactionManager, который представляет собой менеджер транзакций
     *
     * @param entityManagerFactory
     * @return объект JpaTransactionManager
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    private Properties hibernateProperties() {
        var properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql", "false"));
        properties.put("javax.persistence.schema-generation.database.action",
                environment.getProperty("javax.persistence.schema-generation.database.action"));
        properties.put("javax.persistence.schema-generation.create-script-source",
                environment.getProperty("javax.persistence.schema-generation.create-script-source"));
        return properties;
    }
}
