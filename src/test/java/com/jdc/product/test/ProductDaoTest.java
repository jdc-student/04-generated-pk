package com.jdc.product.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.product.config.ApplicationConfig;
import com.jdc.product.model.dao.CategoryDao;
import com.jdc.product.model.dao.ProductDao;
import com.jdc.product.model.dto.Product;

@SpringJUnitConfig(classes = ApplicationConfig.class)
public class ProductDaoTest {

	@Autowired
	private ProductDao pdao;

	@Autowired
	private CategoryDao cdao;

	@Test
	@Order(1)
	@DisplayName("1. Create Product Test")
	@Sql(statements = { "insert into category (name) values ('Foods')",
			"insert into category (name) values ('Drinks')" })
	void test() {
		var category = cdao.findById(1);
		var product = new Product();
		product.setName("Lucky Cake");
		product.setCategory(category);
		product.setPrice(500);

		var id = pdao.create(product);
		Assertions.assertEquals(1, id);
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Find Product By Id")
	void test2() {
		 var p = pdao.findById(1);
		 
		 Assertions.assertNotNull(p);
		 Assertions.assertEquals("Foods",p.getCategory().getName());
		 
		 Assertions.assertEquals("Lucky Cake", p.getName());		 
		 Assertions.assertEquals(500,p.getPrice());
		 
		 Assertions.assertEquals(0,pdao.findById(2));
	}
	
	@Test
	@Order(3)
	@DisplayName("3. Find Products By Category")
	void test3() {
		var list = pdao.findByCategory(1);
		
		Assertions.assertEquals(1,list.size());
		Assertions.assertTrue(pdao.findByCategory(2).isEmpty());
	}
	
	@Test
	@Order(4)
	@DisplayName("4. Search")
	void test4() {
		Assertions.assertEquals(1, pdao.search("Foods").size());
		Assertions.assertEquals(1, pdao.search("Drinks").size());
		Assertions.assertTrue(pdao.search("Foods").isEmpty());
	}
	
	@Test
	@Order(5)
	@DisplayName("5. Update Product")
	void test5() {
		var p = pdao.findById(1);
		p.setName("Pa Pa");
		p.setPrice(300);
		
		int success = pdao.update(p);
		Assertions.assertEquals(1,success);
		
		var other = pdao.findById(1);
		Assertions.assertEquals(p.getName(), other.getName());
		Assertions.assertEquals(p.getPrice(),other.getPrice());
	}
	
	@Test
	@Order(6)
	@DisplayName("6. Delete Product")
	void test6() {
		var success = pdao.delete(1);
		Assertions.assertEquals(1,success);
		Assertions.assertNull(pdao.findById(1));
	}

}
