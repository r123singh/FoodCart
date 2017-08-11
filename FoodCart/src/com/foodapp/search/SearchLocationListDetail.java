package com.foodapp.search;

public class SearchLocationListDetail {

	public static final String TYPE_SEARCH_COMPANY_LOGO_DETAIL = "type_search_company_logo_detail";
	public static final String TYPE_SEARCH_EDIT_LOCATION_DETAIL = "type_search_edit_location_detail";
	public static final String TYPE_SEARCH_CURRENT_LOCATION_DETAIL = "type_search_current_location_detail";
	public static final String TYPE_SEARCH_FIND_RESTAURANT_DETAIL = "type_search_find_restaurant_detail";

	public static final int POS_SEARCH_COMPANY_LOGO_DETAIL = 0;
	public static final int POS_SEARCH_EDIT_LOCATION_DETAIL = 1;
	public static final int POS_SEARCH_CURRENT_LOCATION_DETAIL = 2;
	public static final int POS_SEARCH_FIND_RESTAURANT_DETAIL = 3;

	private long id;
	private String locationstring;
	private String detailtype;
	private String logoimage;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLocationstring() {
		return locationstring;
	}
	public void setLocationstring(String locationstring) {
		this.locationstring = locationstring;
	}
	public String getDetailtype() {
		return detailtype;
	}
	public void setDetailtype(String detailtype) {
		this.detailtype = detailtype;
	}
	public String getLogoimage() {
		return logoimage;
	}
	public void setLogoimage(String logoimage) {
		this.logoimage = logoimage;
	}

}
