package com.foodapp.search;

public class SearchLocation {
	public static final String PLACES_API_KEY = "AIzaSyATo3r84gKzn05B7lgBzCn61Om-G_M90IA";
	public static final String API_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?";
	public static final String PARAM_INPUT = "input=";
	public static final String PARAM_TYPES = "types=geocode";
	public static final String PARAM_SENSOR = "sensor=false";
	public static final String PARAM_KEY = "key=";
	public static final String PARAM_COMPONENTS = "components=country:in";

	private long id;
	private String city;
	private String state;
	private String country;
	private String companylogo;
	private boolean userlocation;
	private String placeid;
	private String description;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public boolean isUserlocation() {
		return userlocation;
	}
	public void setUserlocation(boolean userlocation) {
		this.userlocation = userlocation;
	}
	public String getCompanylogo() {
		return companylogo;
	}
	public void setCompanylogo(String companylogo) {
		this.companylogo = companylogo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlaceid() {
		return placeid;
	}
	public void setPlaceid(String placeid) {
		this.placeid = placeid;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return city + "," + state + "," + country;
	}
}
