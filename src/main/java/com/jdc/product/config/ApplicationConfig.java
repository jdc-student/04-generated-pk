package com.jdc.product.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan("com.jdc.product.model.dao")
@PropertySource("/sql.properties")
public class ApplicationConfig {

	@Bean
	public DataSource dataSource() {
		var builder = new EmbeddedDatabaseBuilder();
		builder.setType(EmbeddedDatabaseType.HSQL);
		builder.setName("dataSource");
		builder.addScript("classpath:/database.sql");

		return builder.build();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NamedParameterJdbcOperations namedJdbcTemplate(JdbcOperations jdbcTemp) {
		return new NamedParameterJdbcTemplate(jdbcTemp);
	}

	@Bean
	public SimpleJdbcInsert categoryInsert(JdbcTemplate jdbcTemp) {
		var insert = new SimpleJdbcInsert(jdbcTemp);
		insert.setTableName("category");
		insert.setGeneratedKeyName("GKN");
		return insert;
	}
}
