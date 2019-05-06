package com.collabera.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collabera.jump.model.Department;
import com.collabera.jump.model.Employee;
import com.collabera.jump.model.Gender;

@Repository
public interface EmsRepo extends JpaRepository<Employee, Integer> {

	public Employee findById(int empId);
	
//	@Query("select e from Employee e where e.address.street = :street")
//	public Employee findByStreet(String street);
	
//	@Query(value = "select ContactNo from EMPLOYEES where e.contactNo = :contactNo?1", nativeQuery = true)
	
//	@Query("select e from Employee e where e.name = :name")
//	public Employee findByName(String name);
	
	public List<Employee> findByName(String name);
	
	public List<Employee> findByGender(Gender gender);
	
	public List<Employee> findByContactNo(String contactNo);
	
	public List<Employee> findByEmail(String email);
	
	public List<Employee> findBySsn(String ssn);
	
	public List<Employee> findByJobTitle(String jobTitle);
	
	public List<Employee> findByDepartment(Department department);
	
	public List<Employee> findBySalary(double salary);
	
	public List<Employee> findByReportsTo(int reportsTo);
	
	public List<Employee> findByIsManager(boolean isManager);
	
}
