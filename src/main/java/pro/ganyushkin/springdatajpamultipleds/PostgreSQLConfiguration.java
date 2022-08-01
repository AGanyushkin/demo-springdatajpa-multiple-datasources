package pro.ganyushkin.springdatajpamultipleds;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "pro.ganyushkin.springdatajpamultipleds.repository.pg",
        entityManagerFactoryRef = "pgEntityManager",
        transactionManagerRef = "pgTransactionManager"
)
@EnableTransactionManagement
public class PostgreSQLConfiguration {

        @Bean(name = "pgDataSource")
        @Primary
        @ConfigurationProperties(prefix = "datasource.pg")
        public DataSource pgDataSource() {
                return DataSourceBuilder.create().build();
        }

        @Bean(name = "pgEntityManager")
        @Primary
        public LocalContainerEntityManagerFactoryBean pgEntityManager(
                @Qualifier("pgDataSource") DataSource plantsDataSource) {
                var em = new LocalContainerEntityManagerFactoryBean();
                em.setDataSource(plantsDataSource);
                em.setPackagesToScan(
                        "pro.ganyushkin.springdatajpamultipleds.entity.shared",
                        "pro.ganyushkin.springdatajpamultipleds.entity.pg"
                );
                var vendorAdapter = new HibernateJpaVendorAdapter();
                em.setJpaVendorAdapter(vendorAdapter);
                HashMap<String, Object> properties = new HashMap<>();
                properties.put("hibernate.hbm2ddl.auto", "validate");
                properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                em.setJpaPropertyMap(properties);
                return em;
        }

        @Bean
        @Primary
        public PlatformTransactionManager pgTransactionManager(
                @Qualifier("pgEntityManager") LocalContainerEntityManagerFactoryBean plantsEntityManager) {
                var transactionManager = new JpaTransactionManager();
                transactionManager.setEntityManagerFactory(plantsEntityManager.getObject());
                return transactionManager;
        }
}
