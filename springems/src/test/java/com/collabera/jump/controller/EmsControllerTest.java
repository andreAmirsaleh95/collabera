package com.collabera.jump.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.collabera.jump.model.Address;
import com.collabera.jump.model.Department;
import com.collabera.jump.model.Employee;
import com.collabera.jump.model.Gender;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testEmsController() throws Exception {
		Date dob = new SimpleDateFormat("MM-dd-yyyy").parse("05-16-1995");
		Address addr = new Address("a", "b", "c", "d", "12345");
		Employee emp = new Employee(2, "Andre2", dob, Gender.OTHER, "1234567890",
				addr, "123456789", "andre@gmail.com", "VP", Department.HR, 50000, 0,
				false);
		ObjectMapper objMapper = new ObjectMapper();
		System.out.println(objMapper.writeValueAsString(emp));

		// Test create:
		mockMvc
				.perform(post("/employee").contentType(MediaType.APPLICATION_JSON)
						.content(objMapper.writeValueAsString(emp)))
				.andExpect(status().isCreated())
				.andExpect(content().string("Employee created."));

		// Test retrieve:
		mockMvc.perform(get("/employee?id=2")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().json(objMapper.writeValueAsString(emp)));

		// Test update:
		String newName = "Jason";
		emp.setName(newName);
		mockMvc
				.perform(
						put("/employee?id=2").contentType(MediaType.APPLICATION_JSON)
								.content(objMapper.writeValueAsString(emp)))
				.andExpect(status().isOk())
				.andExpect(content().string("Employee updated."));
		mockMvc.perform(get("/employee?id=2"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().json(objMapper.writeValueAsString(emp)));

		// Test delete:
		mockMvc.perform(delete("/employee?id=2")).andExpect(status().isOk())
				.andExpect(content().string("Employee deleted."));
		mockMvc.perform(get("/employee?id=2"))
				.andExpect(status().is(HttpStatus.CONFLICT.value()))
				.andExpect(content().string("Employee does not exist."));
	}

}
