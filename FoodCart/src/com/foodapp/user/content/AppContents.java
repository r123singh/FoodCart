package com.foodapp.user.content;

public class AppContents {

	enum Page{
		LOGIN,CART,SEARCH_MENU,CHECKOUT,ADDRESS
	}
	
	enum Login{
		USERNAME,PASSWORD
	}
	
	enum Cart{
		ITEM,ITEM_NAME,ITEM_PRICE,ITEM_QUANTITY,TOTAL_ITEMS
	}
	
}
