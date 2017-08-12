package com.foodvilla.orders;

import java.util.Vector;

public class OrderListBean {

	private boolean valid;
	private Vector<OrdersBean> orderlistVector;
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public Vector<OrdersBean> getOrderlistVector() {
		return orderlistVector;
	}
	public void setOrderlistVector(Vector<OrdersBean> orderlistVector) {
		this.orderlistVector = orderlistVector;
	}

}
