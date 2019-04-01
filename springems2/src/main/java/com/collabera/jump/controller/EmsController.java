package com.collabera.jump.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.collabera.jump.model.Department;
import com.collabera.jump.model.Employee;
import com.collabera.jump.model.Gender;
import com.collabera.jump.service.EmsService;
import com.collabera.jump.util.EmployeeValidater;

@RestController
public class EmsController {

	private static final String INVAID_EMP_ID_MSG = "ID must be between "
			+ EmployeeValidater.MIN_EMP_ID + " and " + EmployeeValidater.MAX_EMP_ID
			+ ".";

	@Autowired
	private EmsService helper;

	@GetMapping(path = "employee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployee(@RequestParam(name = "id") int empId) {
		if (!EmployeeValidater.validateEmpId(empId))
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(INVAID_EMP_ID_MSG);
		Employee emp = helper.getEmployee(empId);
		if (null == emp)
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Employee does not exist.");
		return ResponseEntity.ok(emp);
	}

	@PostMapping(path = "employee", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createEmployee(@RequestBody @Valid Employee emp) {
		if (emp.getEmpId() > 0)
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("ERROR: empId must be non-positive or unspecified.");
		if (emp.getAddress().getAddressId() > 0)
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("ERROR: addressId must be non-positive or unspecified.");
		Employee created = helper.saveEmployee(emp);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@DeleteMapping("employee")
	public ResponseEntity<?> deleteEmployee(
			@RequestParam(name = "id") int empId) {
		if (!EmployeeValidater.validateEmpId(empId))
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(INVAID_EMP_ID_MSG);
		if (null == helper.getEmployee(empId))
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Employee does not exist.");
		helper.deleteEmployee(empId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(path = "employee", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEmployee(@RequestBody @Valid Employee emp) {
		Employee empFromDb = helper.getEmployee(emp.getEmpId());
		if (null == empFromDb)
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Employee does not exist.");
		if (empFromDb.getAddress().getAddressId() != emp.getAddress()
				.getAddressId())
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					"addressId must match that of the address of the employee"
							+ " that is being updated.");
		return ResponseEntity.ok(helper.saveEmployee(emp));
	}

	// ****************************** EMPLOYEES: ******************************

	@GetMapping(path = "employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllEmployees() {
		return ResponseEntity.ok(helper.getAllEmployees());
	}

	@GetMapping(path = "employees/name",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesByName(
			@RequestParam(value = "name") String name) {
		return ResponseEntity.ok(helper.getEmployeesByName(name));
	}

	@GetMapping(path = "employees/gender",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesByGender(
			@RequestParam(value = "gender") Gender gender) {
		return ResponseEntity.ok(helper.getEmployeesByGender(gender));
	}

	@GetMapping(path = "employees/contactno",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesByContactNo(
			@RequestParam(value = "contactNo") String contactNo) {
		return ResponseEntity.ok(helper.getEmployeesByContactNo(contactNo));
	}

	@GetMapping(path = "employees/email",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesByEmail(
			@RequestParam(value = "email") String email) {
		return ResponseEntity.ok(helper.getEmployeesByEmail(email));
	}

	@GetMapping(path = "employees/ssn",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesBySsn(
			@RequestParam(value = "ssn") String ssn) {
		return ResponseEntity.ok(helper.getEmployeesBySsn(ssn));
	}

	@GetMapping(path = "employees/jobtitle",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesByJobTitle(
			@RequestParam(value = "jobtitle") String jobTitle) {
		return ResponseEntity.ok(helper.getEmployeesByJobTitle(jobTitle));
	}

	@GetMapping(path = "employees/department",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesByDepartment(
			@RequestParam(value = "department") Department department) {
		return ResponseEntity.ok(helper.getEmployeesByDepartment(department));
	}

	@GetMapping(path = "employees/salary",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesBySalary(
			@RequestParam(value = "salary") double salary) {
		return ResponseEntity.ok(helper.getEmployeesBySalary(salary));
	}

	@GetMapping(path = "employees/reportsto",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesByReportsTo(
			@RequestParam(value = "reportsto") int reportsTo) {
		return ResponseEntity.ok(helper.getEmployeesByReportsTo(reportsTo));
	}

	@GetMapping(path = "employees/ismanager",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesByIsManager(
			@RequestParam(value = "isManager") boolean isManager) {
		return ResponseEntity.ok(helper.getEmployeesByIsManager(isManager));
	}

}
