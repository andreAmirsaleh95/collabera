/**
 * 
 */
package model;

public class Person {

	protected String name;
	protected int age;
	protected Gender gender;
	protected String contactNo;
	protected Address address;
	
	public Person() {}

	public Person(String name, int age, Gender gender, String contactNo, Address address) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.contactNo = contactNo;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender + ", contactNo=" + contactNo
				+ ", address=" + address + "]";
	}
}
