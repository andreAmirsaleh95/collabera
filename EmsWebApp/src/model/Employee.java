/**
 * 
 */
package model;

public class Employee extends Person implements Comparable<Employee> {

	protected int empId;
	protected String ssn;
	protected String email;
	protected String jobTitle;
	protected Department dept;
	protected double salary;
	protected int reportTo;
	protected boolean isManager;
	
	public Employee() {};

	public Employee(int empId, String name, int age, Gender gender,
			String contactNo, Address address, String ssn, String email,
			String jobTitle, Department dept, double salary, int reportTo,
			boolean isManager) {
		super(name, age, gender, contactNo, address);
		this.empId = empId;
		this.ssn = ssn;
		this.email = email;
		this.jobTitle = jobTitle;
		this.dept = dept;
		this.salary = salary;
		this.reportTo = reportTo;
		this.isManager = isManager;
	}

//	protected static int getUniqueId() {
//		// Read from file:
//		int uniqueId = -1;
//		File f = new File("lastUniqueId.txt");
//		try (FileReader fr = new FileReader(f);
//				BufferedReader br = new BufferedReader(fr);) {
//			uniqueId = Integer.parseInt(br.readLine()) + 1;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		// Write to file:
//		if (0 < uniqueId) {
//			try  (FileWriter fw = new FileWriter(f);) {
//				fw.write(Integer.toString(uniqueId));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		return uniqueId;
//	}
	
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

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getReportTo() {
		return reportTo;
	}

	public void setReportTo(int reportTo) {
		this.reportTo = reportTo;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Employee
				&& this.getEmpId() == ((Employee) obj).getEmpId()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Integer.parseInt(this.getSsn());
	}

	@Override
	public int compareTo(Employee o) {
		return (this.getName().length() - o.getName().length());
	}

	@Override
	public String toString() {
		return "(*)EmployeeId = " + empId + " <===> Name = " + getName()
				+ ", Age = " + getAge() + ", Gender = " + getGender()
				+ ", ContactNo = " + getContactNo()
				+ ",\n                        Address = " + getAddress()
				+ ",\n                        SSN = " + ssn + ", eMail = " + email
				+ ", Job Title = " + jobTitle + ", Department = " + dept
				+ ", Salary = " + salary + ", reportTo = " + reportTo
				+ ", isManager = " + isManager;
	}
}