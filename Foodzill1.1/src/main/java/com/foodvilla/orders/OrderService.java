package com.foodvilla.orders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import com.foodvilla.restaurant.RestaurantBean;
import com.foodvilla.restaurant.RestaurantService;

/**
 * @author Ramandeep Singh
 * 
 */
public class OrderService {

	private static final String ORDER_CREATED = "NEW_ORDER";

	public static OrdersBean createnewfoodorder(OrdersBean orderBean) {
		JSONObject neworderjson = null;
		OrderDAO orderdao = null;
		try {
			orderdao = new OrderDAO();
			neworderjson = orderBean.getNeworderjsonrequest();
			JSONObject orderjson = neworderjson.getJSONObject("order");
			FoodOrder foodorder = new FoodOrder();
			foodorder.setTotalPrice(orderjson.getDouble("order_total_amount"));
			foodorder.setTotalTax(orderjson.getDouble("order_total_tax"));
			foodorder.setDeliveryAddress(orderjson.getString("order_delivery_address"));
			foodorder.setDelivery(orderjson.getDouble("order_delivery_charge"));
			foodorder.setPackaging(orderjson.getDouble("order_packaging_charge"));
			foodorder.setSubtotal(orderjson.getDouble("order_subtotal"));
			String orderdate = orderjson.getString("order_delivery_date");
			SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
			foodorder.setOrderDate(dateformat.parse(orderdate));
			foodorder.setCustomerMobile(orderjson.getString("order_customer_mobile"));
			foodorder.setRestaurantId(orderjson.getLong("restaurant_id"));
			foodorder.setStatus(ORDER_CREATED);
			JSONArray orderitems = orderjson.getJSONArray("orderitems");
			for (int i = 0; i < orderitems.length(); i++) {
				JSONObject orderitemjson = orderitems.getJSONObject(i);
				Orderitem orderitem = new Orderitem();
				orderitem.setName(orderitemjson.getString("order_item_name"));
				orderitem.setPrice(orderitemjson.getDouble("order_item_price"));
				orderitem.setSubtotal(orderitemjson.getDouble("order_item_subtotal"));
				orderitem.setRemarks(orderitemjson.getString("order_item_remarks"));
				orderitem.setQuantity(orderitemjson.getInt("order_item_quantity"));
				orderitem.setDescription(orderitemjson.getString("order_item_description"));
				orderitem.setFoodOrder(foodorder);
				OrderItemBean orderitembean = new OrderItemBean();
				orderitembean.setOrderitem(orderitem);
				orderitembean = orderdao.createneworderitem(orderitembean);
				if (!orderitembean.isValid()) {
					orderBean.setErrormsg(orderitembean.getErrormsg());
				}
			}

			orderBean.setValid(true);
		} catch (Exception e) {
			e.printStackTrace();
			orderBean.setValid(false);
			orderBean.setErrormsg("Oops! Orders Times are Closed");
		}
		return orderBean;
	}

	public static OrderListBean fetchallOrders(OrderListBean orderListBean) {
		FoodOrdersBean foodOrdersBean = new FoodOrdersBean();
		foodOrdersBean.setValid(false);
		Vector<OrdersBean> orderlistVector = null;
		foodOrdersBean = OrderDAO.fetchallOrders(foodOrdersBean);
		if (foodOrdersBean.isValid() && foodOrdersBean.getFoodOrders() != null) {
			List<FoodOrder> foodOrders = foodOrdersBean.getFoodOrders();
			orderlistVector = new Vector<OrdersBean>();
			for (FoodOrder foodOrder : foodOrders) {
				OrdersBean ordersBean = new OrdersBean();
				// ordersBean.setOrderId(foodOrder.getOrderid());
				ordersBean.setDeliveryAddress(foodOrder.getDeliveryAddress());
				ordersBean.setStatus(foodOrder.getStatus());
				ordersBean.setTotalPrice(foodOrder.getTotalPrice());
				ordersBean.setTotalTax(foodOrder.getTotalTax());
				ordersBean.setOrderdate(foodOrder.getOrderDate());
				ordersBean.setValid(false);
				ordersBean = OrderDAO.fetchallOrderitems(ordersBean);
				if (!ordersBean.getOrderItems().isValid()) {
					ordersBean.setValid(false);
				} else {
					ordersBean.setValid(true);
				}
				orderlistVector.add(ordersBean);
			}
		}
		orderListBean.setOrderlistVector(orderlistVector);

		return orderListBean;
	}

	public static FoodOrdersBean syncfoodordersfordeliveryguy(FoodOrdersBean foodorderbean) {
		OrderDAO orderDAO = null;
		ArrayList<FoodOrder> foodOrders = null;
		JSONObject pendingordersjson = null;
		try {
			long restaurantid = Long.parseLong(foodorderbean.getFoodorderrestaurant());
			RestaurantBean restaurantBean = new RestaurantBean();
			restaurantBean.setValid(false);
			restaurantBean.setRestaurantid(restaurantid);
			restaurantBean = RestaurantService.fetchrestaurantbyrestaurantid(restaurantBean);
			if (restaurantBean.isValid()) {
				pendingordersjson = new JSONObject();
				pendingordersjson.put("restaurant_name", restaurantBean.getRestaurant().getName());
				pendingordersjson.put("restaurant_address", restaurantBean.getRestaurant().getAddress());
				pendingordersjson.put("restaurant_id", restaurantid);
				orderDAO = new OrderDAO();
				foodorderbean = orderDAO.fetchallpendingneworders(foodorderbean, restaurantid);
				if (foodorderbean.isValid()) {
					foodOrders = foodorderbean.getFoodOrders();
					JSONArray orderjsonarray = new JSONArray();
					for (FoodOrder foodorder : foodOrders) {
						JSONObject orderjson = new JSONObject();
						orderjson.put("order_id", foodorder.getOrderid());
						orderjson.put("order_total_amount", foodorder.getTotalPrice());
						orderjson.put("order_total_tax", foodorder.getTotalTax());
						orderjson.put("order_delivery_address", foodorder.getDeliveryAddress());
						orderjson.put("order_delivery_date", foodorder.getOrderDate());
						orderjson.put("order_status", foodorder.getStatus());
						orderjson.put("order_subtotal", foodorder.getSubtotal());
						orderjson.put("order_delivery_charge", foodorder.getDelivery());
						orderjson.put("order_packaging_charge", foodorder.getPackaging());
						orderjson.put("order_customer_mobile", foodorder.getCustomerMobile());
						OrderItemsBeans orderItemsBeans = new OrderItemsBeans();
						orderItemsBeans.setFoodOrder(foodorder);
						orderItemsBeans = orderDAO.fetchallorderitemsbyorderid(orderItemsBeans);
						if (orderItemsBeans.isValid()) {
							ArrayList<Orderitem> orderitemslist = orderItemsBeans.getOrderitemlist();
							JSONArray orderitemsarray = new JSONArray();
							for (Orderitem orderitem : orderitemslist) {
								JSONObject orderitemjsonobject = new JSONObject();
								orderitemjsonobject.put("order_item_id", orderitem.getOrderitemid());
								orderitemjsonobject.put("order_item_name", orderitem.getName());
								orderitemjsonobject.put("order_item_price", orderitem.getPrice());
								orderitemjsonobject.put("order_item_quantity", orderitem.getQuantity());
								orderitemjsonobject.put("order_item_remarks", orderitem.getRemarks());
								orderitemjsonobject.put("order_item_subtotal", orderitem.getSubtotal());
								orderitemsarray.put(orderitemjsonobject);
							}
							orderjson.put("orderitems", orderitemsarray);
						} else {
							orderjson.put("orderitems_error", orderItemsBeans.getErrorMessage());
						}
						orderjsonarray.put(orderjson);
					}
					pendingordersjson.put("orders", orderjsonarray);
				} else {
					foodorderbean.setValid(false);
					foodorderbean.setErrormessage(restaurantBean.getErrormessage());
				}
				foodorderbean.setFoodorderjsonresponse(pendingordersjson);
				foodorderbean.setValid(true);
			} else {
				foodorderbean.setValid(false);
				foodorderbean.setErrormessage(restaurantBean.getErrormessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			foodorderbean.setErrormessage(e.getLocalizedMessage());
			foodorderbean.setValid(false);
		}
		return foodorderbean;
	}

	public static OrdersBean fetchorderstatusbyid(OrdersBean orderbean) {
		OrderDAO orderdao = null;
		JSONObject orderstatusjson = null;
		try {
			orderdao = new OrderDAO();
			orderstatusjson = new JSONObject();
			long orderid = Long.parseLong(orderbean.getOrderId());
			orderbean = orderdao.fetchfoodorderbyid(orderbean, orderid);
			if (!orderbean.isValid()) {
				orderbean.setErrormsg("Invalid Order Placed");
			} else {
				if (orderbean.getFoodOrder() == null) {
					orderbean.setErrormsg("Invalid Order");
					orderbean.setValid(false);
				} else {
					orderstatusjson.put("order_id", orderbean.getFoodOrder().getOrderid());
					orderstatusjson.put("order_status", orderbean.getFoodOrder().getStatus());
					orderbean.setOrderstatusjson(orderstatusjson);
				}
			}
		} catch (Exception e) {
			orderbean.setValid(false);
			orderbean.setErrormsg("Invalid Order Placed");
		}
		return orderbean;
	}

	public static OrdersBean markorderasdelivered(OrdersBean orderbean) {
		OrderDAO orderdao = null;
		JSONObject markdeliveredresponse = new JSONObject();
		try {
			orderdao = new OrderDAO();
			orderbean = orderdao.fetchfoodorderbyid(orderbean, Long.parseLong(orderbean.getOrderId()));
			if (orderbean.getFoodOrder() != null && orderbean.isValid()) {
				FoodOrder order = orderbean.getFoodOrder();
				order.setStatus("DELIVERED");
				orderbean.setFoodOrder(order);
				orderbean = orderdao.markaorderdelivered(orderbean);
				markdeliveredresponse.put("order_id", order.getOrderid());
				markdeliveredresponse.put("order_status", orderbean.getStatus());
				orderbean.setOrderstatusjson(markdeliveredresponse);
			} else if (!orderbean.isValid()) {
				orderbean.setErrormsg("Order does not exist");
				orderbean.setValid(false);
				markdeliveredresponse.put("order_error", orderbean.getErrormsg());
				markdeliveredresponse.put("order_id", Long.parseLong(orderbean.getOrderId()));
				orderbean.setOrderstatusjson(markdeliveredresponse);
			}
		} catch (Exception e) {
			orderbean.setValid(false);
			orderbean.setErrormsg("Order does not exist");
		}
		return orderbean;
	}
}
