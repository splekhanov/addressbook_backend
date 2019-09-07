package com.addressbook.configuration;

import com.addressbook.constants.Constants;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@EntityScan(basePackages = {Constants.ENTITY_BASE_PACKAGE})
@EnableJpaRepositories(basePackages = {Constants.REPOSITORY_BASE_PACKAGE})
@EnableSpringDataWebSupport
@EnableTransactionManagement
@EnableConfigurationProperties({DataSourceProperties.class, JpaProperties.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class JpaConfiguration {

    private static final String LOG_TAG = "[DATA_SOURCE_CONFIGURATION] ::";
    private final DataSourceProperties dataSourceProperties;
    private final JpaProperties jpaProperties;

    @Autowired
    public JpaConfiguration(DataSourceProperties dsProperties, JpaProperties jpaProperties) {
        this.dataSourceProperties = dsProperties;
        this.jpaProperties = jpaProperties;
    }

    @PostConstruct
    public void init() {
        if (log.isInfoEnabled()) {
            log.info(
                    "{} has been initialized",
                    LOG_TAG
            );
            log.info(
                    "{} database url : {}, database user : {}",
                    LOG_TAG,
                    dataSourceProperties.getUrl(),
                    dataSourceProperties.getUsername()
            );
        }
    }

    @Bean(name = "dataSource")
    public HikariDataSource dataSource() {
        return (HikariDataSource) dataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver() {
        return new PageableHandlerMethodArgumentResolver();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SortHandlerMethodArgumentResolver sortHandlerMethodArgumentResolver() {
        return new SortHandlerMethodArgumentResolver();
    }

}
