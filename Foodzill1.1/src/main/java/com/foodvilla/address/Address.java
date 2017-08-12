package com.foodvilla.address;

public class Address {

	private String firstline;
	private String secondline;
	private String addressid;
	private String thridline;
	private String city;
	private String pincode;
	private String state;
	private AddressType addressType;

	enum AddressType {
		DELIVERY, RESTAURANT
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

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}

	public String getThridline() {
		return thridline;
	}

	public void setThridline(String thridline) {
		this.thridline = thridline;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

}
