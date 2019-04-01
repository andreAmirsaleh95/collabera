/**
 * 
 */
package com.collabera.jump.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Andre Amirsaleh
 */
@Entity
@Table(name = "employees")
public class Employee extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Min(value = 0, message = "ID must be between 0 and 999999")
	@Max(value = 999999, message = "ID must be between 0 and 999999")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public boolean setIsManager() {
		return isManager;
	}

	public void getIsManager(boolean isManager) {
		this.isManager = isManager;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", ssn=" + ssn + ", email=" + email
				+ ", jobTitle=" + jobTitle + ", department=" + department
				+ ", salary=" + salary + ", reportsTo=" + reportsTo + ", isManager="
				+ isManager + "]";
	}
	
}