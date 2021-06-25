package com.upwordsapi.upwords;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UpwordsApplicationTests {

	@Autowired
	private BoardStateController controller;

	@Test
	public void contextLoads() {
		assertNotNull(controller);
	}
}
