package com.safty.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

	@Bean("commonDruidDataProperties")
	@ConfigurationProperties(prefix = "spring.datasource.common")
	public Map<String, String> commonDruidDataProperties() {
		return new HashMap<String, String>();
	}

	@Bean(name = "commonDataSource")
	@Primary
	public DataSource druidDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		MutablePropertyValues properties = new MutablePropertyValues(commonDruidDataProperties());
		new RelaxedDataBinder(dataSource).bind(properties);
		return dataSource;
	}

}
