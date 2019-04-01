package com.collabera.jump.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.collabera.jump.model.Employee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class EmsControllerUnitTest {

	@LocalServerPort
	private int port;

	private TestRestTemplate trt = new TestRestTemplate(
			new RestTemplateBuilder());

	@Test
	public void getEmployee() {
		ResponseEntity<Employee> response = trt.getForEntity(
				"http://localhost:8080" + port + "/employee?id=1", Employee.class);
		assertTrue(200 == response.getStatusCodeValue());
		Employee empFromDb = response.getBody();
		assertTrue(null != empFromDb);
		assertTrue("Andre".equals(empFromDb.getName()));
	}

}
