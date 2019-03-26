/**
 * 
 */
package com.collabera.jump.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Andre Amirsaleh
 */
public class Manager extends Employee {

	protected Map<Integer, Employee> team = new HashMap<Integer, Employee>();

	public Manager(int empId, String name, Date dob, Gender gender,
			String contactNo, Address address, String ssn, String email,
			String jobTitle, Department department, double salary, int reportsTo) {
		super(empId, name, dob, gender, contactNo, address, ssn, email, jobTitle,
				department, salary, reportsTo, true);
	}

	public Map<Integer, Employee> getTeam() {
		return team;
	}

	public void setTeam(Map<Integer, Employee> team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "(*)ManagerId = " + getEmpId() + " <===> Name = " + getName()
				+ ", Age = " + getDob() + ", Gender = " + getGender()
				+ ", ContactNo = " + getContactNo()
				+ ",\n                       Address = " + getAddress()
				+ ",\n                       SSN = " + getSsn() + ", eMail = "
				+ getEmail() + ", Job Title = " + getJobTitle() + ", Department = "
				+ getDepartment() + ", Salary = " + getSalary() + ", reportTo = "
				+ getReportsTo() + ", isManager = " + isManager() + ",\n    "
				+ team.size() + " Employee/s <===>  Team = " + team;
	}
}
