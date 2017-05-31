package com.system.boot.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "commonDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.common")
    public DataSource commonDataSource() {
        return DataSourceBuilder.create().build();
    }

}
