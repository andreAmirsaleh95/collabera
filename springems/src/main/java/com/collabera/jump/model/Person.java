/**
 * 
 */
package com.collabera.jump.model;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Andre Amirsaleh
 */
public class Person {

	@Size(min = 1, max = 80)
	protected String name;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	protected Date dob;

	protected Gender gender;

	@Size(min = 10, max = 10)
	@Pattern(regexp = "\\d{10}", message = "Contact number must be exactly 10 digits")
	protected String contactNo;

	@Valid
	protected Address address;

	public Person() {
	}

	public Person(String name, Date dob, Gender gender, String contactNo,
			Address address) {
		this.name = name;
		this.dob = dob;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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
		return "Person [name=" + name + ", age=" + dob + ", gender=" + gender
				+ ", contactNo=" + contactNo + ", address=" + address + "]";
	}
}
