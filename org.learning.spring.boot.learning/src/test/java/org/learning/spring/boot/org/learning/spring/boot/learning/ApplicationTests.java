package org.learning.spring.boot.org.learning.spring.boot.learning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.learning.spring.boot.learning.Application;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
class ApplicationTests {

	@Test
	void contextLoads() {
		assertEquals("1", "1");
	}
}
