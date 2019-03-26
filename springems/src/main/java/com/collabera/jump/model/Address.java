/**
 * 
 */
package com.collabera.jump.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Andre Amirsaleh
 */
public class Address {

	@Size(min = 0, max = 100, message = "Street address must be at most 100 characters.")
	private String streetAddress;
	
	@Size(min = 0, max = 100, message = "City must be at most 100 characters.")
	private String city;
	
	@Size(min = 0, max = 100, message = "State must be at most 100 characters.")
	private String state;
	
	@Size(min = 0, max = 100, message = "Country must be at most 100 characters.")
	private String country;
	
	@Size(min = 5, max = 5, message = "Zip code must be at exactly 5 digits.")
	@Pattern(regexp = "[0-9]{5}")
	private String zipCode;

	public Address(String streetAddress, String city, String state,
			String country, String zipCode) {
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return streetAddress + ", " + city + ", " + state + ", " + zipCode;
	}
}