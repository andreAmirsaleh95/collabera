/**
 * 
 */
package model;

public class Address {

	private String streetAddress;
	private String city;
	private String state;
	private String country;
	private int zipCode;
	private String fullAddress;
	
	public Address(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public Address(String streetAddress, String city, String state,
			String country, int zipCode) {
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
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

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		if (null != fullAddress)
			return fullAddress;
		return streetAddress + ", " + city + ", " + state + ", " + zipCode;
	}
}