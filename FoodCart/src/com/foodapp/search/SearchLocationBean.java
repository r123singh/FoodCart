package com.foodapp.search;

public class SearchLocationBean {

	private SearchLocation searchLocation;
	private boolean userlocation;
	private boolean valid;
	private String errormsg;
	
	public SearchLocation getSearchLocation() {
		return searchLocation;
	}
	public void setSearchLocation(SearchLocation searchLocation) {
		this.searchLocation = searchLocation;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public boolean isUserlocation() {
		return userlocation;
	}
	public void setUserlocation(boolean userlocation) {
		this.userlocation = userlocation;
	}

}
