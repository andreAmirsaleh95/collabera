package com.collabera.jump.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.collabera.jump.model.Address;
import com.collabera.jump.model.Department;
import com.collabera.jump.model.Employee;
import com.collabera.jump.model.Gender;
import com.collabera.jump.service.EmployeeValidater;

@RestController
public class EmsController {

	private static final String INVAID_EMP_ID_MSG = "ID must be between "
			+ EmployeeValidater.MIN_EMP_ID + " and " + EmployeeValidater.MAX_EMP_ID
			+ ".";

	/**
	 * Employee database. Key is empId. Value is Employee object.
	 */
	private Map<Integer, Employee> employees;

	{
		employees = new HashMap<Integer, Employee>();
		Address exampleAddr = new Address("12 Blah Rd", "NYC", "NY", "US",
				"12345");
		Employee exampleEmp = new Employee(1, "Andre Anderson", new Date(),
				Gender.FEMALE, "1234567890", exampleAddr, "123456789",
				"andre@collabera.com", "CEO", Department.DEVELOPMENT, 50000, 0,
				false);
		employees.put(exampleEmp.getEmpId(), exampleEmp);
	}
	
	@GetMapping(path = "employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllEmployees() {
		return ResponseEntity.ok(employees);
	}

	@GetMapping(path = "employee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployee(@RequestParam(name = "id") int empId) {
		if (!EmployeeValidater.validateEmpId(empId))
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(INVAID_EMP_ID_MSG);
		Employee emp = employees.get(empId);
		if (null == emp)
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Employee does not exist.");
		return ResponseEntity.ok(employees.get(empId));
	}

	@PostMapping(path = "employee", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createEmployee(@RequestBody @Valid Employee emp) {
		employees.put(emp.getEmpId(), emp);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Employee created.");
	}

	@DeleteMapping("employee")
	public ResponseEntity<?> deleteEmployee(
			@RequestParam(name = "id") int empId) {
		if (!EmployeeValidater.validateEmpId(empId))
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(INVAID_EMP_ID_MSG);
		if (null == employees.remove(empId))
			return ResponseEntity.badRequest().body("Employee does not exist.");
		return ResponseEntity.ok("Employee deleted.");
	}

	@PutMapping(path = "employee", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEmployee(
			@RequestParam(name = "id") int oldEmpId,
			@RequestBody @Valid Employee emp) {
		if (!EmployeeValidater.validateEmpId(oldEmpId))
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(INVAID_EMP_ID_MSG);
		if (null == employees.remove(oldEmpId)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Employee does not exist.");
		} else {
			try {
				employees.put(emp.getEmpId(), emp);
			} catch (IllegalArgumentException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Unique ID required.");
			}
			return ResponseEntity.ok("Employee updated.");
		}
	}

}
