package com.jdc.product.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.product.config.ApplicationConfig;
import com.jdc.product.model.dao.CategoryDao;
import com.jdc.product.model.dto.Category;

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(classes = ApplicationConfig.class)
public class CategoryDaoTest {

	@Autowired
	private CategoryDao dao;

	@Test
	@Order(1)
	void test() {
		var c = new Category();
		c.setName("Food");

		var id = dao.create(c);
		Assertions.assertEquals(1, id);
	}

}
