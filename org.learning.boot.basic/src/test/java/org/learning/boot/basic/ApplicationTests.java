package org.learning.boot.basic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.learning.boot.basic.jpa.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@LocalServerPort // random port on which http calls for testing will be made
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	/**
	 * Tests for HelloController
	 */

	@Test
	public void testHello() {
		String url = "http://localhost:" + port + "/hello";
		ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);

		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(res.getBody()).isEqualTo("Welcome from HelloController");
	}

	@Test
	public void testBye() {
		String url = "http://localhost:" + port + "/bye";
		ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);

		assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(res.getBody()).isEqualTo("Bye from HelloController");
	}

	/**
	 * Tests for UserController
	 */

	@Test
	public void testUserCreation() {
		Users user = new Users();
		user.setName("Test User");
		user.setEmail("test@example.com");
		user.setRole("USER");

		// POST request to create user
		ResponseEntity<Users> createResponse = restTemplate.postForEntity("http://localhost:" + port + "/users", user, // Request
																														// body
				Users.class // Response type
		);

		// Verify user was created correctly
		assertThat(createResponse.getBody()).isNotNull();
		assertThat(createResponse.getBody().getName()).isEqualTo("Test User");
	}
}
