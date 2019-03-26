/**
 * 
 */
package com.collabera.jump.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Andre Amirsaleh
 */
public class Employee extends Person {

	@Min(value = 0, message = "ID must be between 0 and 999999")
	@Max(value = 999999, message = "ID must be between 0 and 999999")
	protected int empId;
	
	@Size(min = 9, max = 9, message = "ssn must be exactly 9 digits.")
	@Pattern(regexp = "[0-9]{9}")
	protected String ssn;
	
	@Size(min = 0, max = 100)
	@Email
	protected String email;
	
	@Size(min = 0, max = 100, message = "jobTitle must be at most 100 characters.")
	protected String jobTitle;
	
	protected Department department;
	
	@Min(value = 0, message = "salary must be between 0 and 999999")
	@Max(value = 999999, message = "salary must be between 0 and 999999")
	protected double salary;
	
	@Min(value = 0)
	@Max(value = 999999)
	protected int reportsTo;
	
	protected boolean isManager;
	
	public Employee() {};

	public Employee(int empId, String name, Date dob, Gender gender,
			String contactNo, Address address, String ssn, String email,
			String jobTitle, Department department, double salary, int reportsTo,
			boolean isManager) {
		super(name, dob, gender, contactNo, address);
		this.empId = empId;
		this.ssn = ssn;
		this.email = email;
		this.jobTitle = jobTitle;
		this.department = department;
		this.salary = salary;
		this.reportsTo = reportsTo;
		this.isManager = isManager;
	}
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department dept) {
		this.department = dept;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	@Override
	public String toString() {
		return "(*)EmployeeId = " + empId + " <===> Name = " + getName()
				+ ", Age = " + getDob() + ", Gender = " + getGender()
				+ ", ContactNo = " + getContactNo()
				+ ",\n                        Address = " + getAddress()
				+ ",\n                        SSN = " + ssn + ", eMail = " + email
				+ ", Job Title = " + jobTitle + ", Department = " + department
				+ ", Salary = " + salary + ", reportTo = " + reportsTo
				+ ", isManager = " + isManager;
	}
}