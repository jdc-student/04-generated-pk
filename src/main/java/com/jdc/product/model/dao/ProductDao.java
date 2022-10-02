package com.jdc.product.model.dao;

import java.util.HashMap;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.jdc.product.model.dto.Product;

@Repository

public class ProductDao {

	@Autowired
	private NamedParameterJdbcOperations jdbc;

	@Value("${dao.product.insert}")
	private String create;
	@Value("${dao.product.update}")
	private String update;
	@Value("${dao.product.delete}")
	private String delete;
	@Value("${dao.product.findById}")
	private String findById;
	@Value("${dao.product.findByCategoryId}")
	private String findByCategoryId;
	@Value("${dao.product.search}")
	private String search;
	
	private RowMapper<Product> rowMapper;
	
	public ProductDao() {
		this.rowMapper = new BeanPropertyRowMapper<>(Product.class);
	}

	public int create(Product p) {
		var key = new GeneratedKeyHolder();

		var params = new MapSqlParameterSource();
		params.addValue("name", p.getName());
		params.addValue("categoryId", p.getCategory().getId());
		params.addValue("price", p.getPrice());

		jdbc.update(create, params, key);

		return key.getKey().intValue();
	}

	public Product findById(int id) {
		var params = new HashMap<String,Object>();
		params.put("id", id);
		return jdbc.queryForObject(findById, params, rowMapper);
	}

	public List<Product> findByCategory(int id) {
		var params = new HashMap<String,Object>();
		params.put("categoryId", id);
		return jdbc.query(findByCategoryId, params, rowMapper);
	}

	public List<Product> search(String keyword) {
		var params = new HashMap<String,Object>();
		params.put("keyword", keyword.toLowerCase().concat("%"));
		return jdbc.query(search, params, rowMapper);
	}

	public int update(Product p) {
		var params = new MapSqlParameterSource();
		params.addValue("name", p.getName());
		params.addValue("price", p.getPrice());
		params.addValue("id", p.getId());
		
		return jdbc.update(update, params);
	}

	public int delete(int id) {
		var params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return jdbc.update(delete, params);
	}
}
