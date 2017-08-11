package com.foodapp.address;


public class Address{

	private String firstline;
	private String secondline;
	private String thirdline;
	private String pincode;
	private String city;
	private String state;
	private String contact;
	private AddressType addressType;

	enum AddressType {
		RESTAURANT, CUSTOMER
	}

	public String getFirstline() {
		return firstline;
	}

	public void setFirstline(String firstline) {
		this.firstline = firstline;
	}

	public String getSecondline() {
		return secondline;
	}

	public void setSecondline(String secondline) {
		this.secondline = secondline;
	}

	public String getThirdline() {
		return thirdline;
	}

	public void setThirdline(String thirdline) {
		this.thirdline = thirdline;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

}
