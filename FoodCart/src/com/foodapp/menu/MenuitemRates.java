package com.foodapp.menu;

public class MenuitemRates {

	private String ratename;
	private int price;
	private boolean deliverchargesApplicable;
	private String ratedescription;
	public String getRatename() {
		return ratename;
	}
	public void setRatename(String ratename) {
		this.ratename = ratename;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isDeliverchargesApplicable() {
		return deliverchargesApplicable;
	}
	public void setDeliverchargesApplicable(boolean deliverchargesApplicable) {
		this.deliverchargesApplicable = deliverchargesApplicable;
	}
	public String getRatedescription() {
		return ratedescription;
	}
	public void setRatedescription(String ratedescription) {
		this.ratedescription = ratedescription;
	}

}
