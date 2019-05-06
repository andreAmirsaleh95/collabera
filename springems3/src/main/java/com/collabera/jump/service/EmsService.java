package com.collabera.jump.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabera.jump.model.Department;
import com.collabera.jump.model.Employee;
import com.collabera.jump.model.Gender;
import com.collabera.jump.repository.EmsRepo;

@Service
public class EmsService {
	
	@Autowired
	private EmsRepo repo;
	
	public Employee saveEmployee(Employee emp) {
		return repo.save(emp);
	}
	
	public Employee getEmployee(int empId) {
		return repo.findById(empId);
	}
	
	public boolean deleteEmployee(int empId) {
		repo.deleteById(empId);
		return true;
	}
	
	public Iterable<Employee> getAllEmployees() {
		return repo.findAll();
	}
	
	public Iterable<Employee> getEmployeesByName(String nameToQuery) {
		return repo.findByName(nameToQuery);
	}
	
	public Iterable<Employee> getEmployeesByGender(Gender gender) {
		return repo.findByGender(gender);
	}
	
	public Iterable<Employee> getEmployeesByContactNo(String contactNo) {
		return repo.findByContactNo(contactNo);
	}

	public Iterable<Employee> getEmployeesByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public Iterable<Employee> getEmployeesBySsn(String ssn) {
		return repo.findBySsn(ssn);
	}
	
	public Iterable<Employee> getEmployeesByJobTitle(String jobTitle) {
		return repo.findByJobTitle(jobTitle);
	}

	public Iterable<Employee> getEmployeesByDepartment(Department department) {
		return repo.findByDepartment(department);
	}

	public Iterable<Employee> getEmployeesBySalary(double salary) {
		return repo.findBySalary(salary);
	}

	public Iterable<Employee> getEmployeesByReportsTo(int reportsTo) {
		return repo.findByReportsTo(reportsTo);
	}

	public Iterable<Employee> getEmployeesByIsManager(boolean isManager) {
		return repo.findByIsManager(isManager);
	}
	
	public EmsRepo getRepo() {
		return repo;
	}
	
}
