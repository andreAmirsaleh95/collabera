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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

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
		Address addr = new Address();
		addr.setStreet("123 Smith Rd");
		addr.setCity("Townville");
		addr.setState("NJ");
		addr.setCountry("USA");
		addr.setZip("12345");
		Employee emp = new Employee();
		emp.setName("Andre");
		emp.setDob(dob);
		emp.setGender(Gender.MALE);
		emp.setContactNo("1234567890");
		emp.setAddress(addr);
		emp.setSsn("123456789");
		emp.setEmail("andre@gmail.com");
		emp.setJobTitle("CEO");
		emp.setDepartment(Department.HR);
		emp.setSalary(50000);
		emp.setReportsTo(0);
		emp.getIsManager(false);
		ObjectMapper objMapper = new ObjectMapper();

//		System.out.println(objMapper.writeValueAsString(emp));

		// Test create:
		ResultActions resultActions = mockMvc
				.perform(post("/employee").contentType(MediaType.APPLICATION_JSON)
						.content(objMapper.writeValueAsString(emp)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		Employee empCreated = objMapper.readValue(contentAsString,
				Employee.class);
		emp.setEmpId(empCreated.getEmpId());
		emp.getAddress().setAddressId(empCreated.getAddress().getAddressId());

		// Test retrieve:
		mockMvc.perform(get("/employee?id=" + emp.getEmpId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().json(objMapper.writeValueAsString(emp)));

		// Test update:
		String newName = "Jason";
		emp.setName(newName);
		emp.setSalary(0);
		mockMvc
				.perform(put("/employee?id=" + emp.getEmpId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objMapper.writeValueAsString(emp)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

//		result = resultActions.andReturn();
//		contentAsString = result.getResponse().getContentAsString();
//		empCreated = objMapper.readValue(contentAsString, Employee.class);
//		emp.setEmpId(empCreated.getEmpId());
//		emp.getAddress().setAddressId(empCreated.getAddress().getAddressId());

		mockMvc.perform(get("/employee?id=" + emp.getEmpId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().json(objMapper.writeValueAsString(emp)));

		// Test delete:
		mockMvc.perform(delete("/employee?id=" + emp.getEmpId()))
				.andExpect(status().isNoContent());
	}

}
