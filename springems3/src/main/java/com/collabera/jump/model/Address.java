/**
 * 
 */
package com.collabera.jump.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Andre Amirsaleh
 */
@Entity
public class Address {
	
	@GeneratedValue
	@Id
	private int addressId;

	@Size(min = 0, max = 100, message = "Street address must be at most 100 characters.")
	private String street;
	
	@Size(min = 0, max = 100, message = "City must be at most 100 characters.")
	private String city;
	
	@Size(min = 0, max = 100, message = "State must be at most 100 characters.")
	private String state;
	
	@Size(min = 0, max = 100, message = "Country must be at most 100 characters.")
	private String country;
	
	@Size(min = 5, max = 5, message = "Zip code must be at exactly 5 digits.")
	@Pattern(regexp = "[0-9]{5}")
	private String zip;
	
	public int getAddressId() {
		return addressId;
	}
	
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return street + ", " + city + ", " + state + ", " + zip;
	}
}