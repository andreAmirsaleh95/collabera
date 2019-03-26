/**
 * 
 */
package model;

import java.util.HashMap;
import java.util.Map;

public class Manager extends Employee {

	protected Map<Integer, Employee> team = new HashMap<Integer, Employee>();

	public Manager(int empId, String name, int age, Gender gender, String contactNo,
			Address address, String ssn, String email, String jobTitle,
			Department dept, double salary, int reportTo) {
		super(empId, name, age, gender, contactNo, address, ssn, email, jobTitle, dept,
				salary, reportTo, true);
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
				+ ", Age = " + getAge() + ", Gender = " + getGender()
				+ ", ContactNo = " + getContactNo()
				+ ",\n                       Address = " + getAddress()
				+ ",\n                       SSN = " + getSsn() + ", eMail = "
				+ getEmail() + ", Job Title = " + getJobTitle() + ", Department = "
				+ getDept() + ", Salary = " + getSalary() + ", reportTo = "
				+ getReportTo() + ", isManager = " + isManager() + ",\n    "
				+ team.size() + " Employee/s <===>  Team = " + team;
	}
}
