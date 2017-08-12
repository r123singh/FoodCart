package com.foodvilla.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.foodvilla.sess.FoodzillaSession;

public class OrderDAO {

	public static NewOrderBean getNewOrder(NewOrderBean newOrderBean) {
		FoodOrder foodOrder = null;
		try {
			Session orderSession = FoodzillaSession.openCurrentSession();
			foodOrder = (FoodOrder) orderSession.get(FoodOrder.class, newOrderBean.getOrderid());
			newOrderBean.setFoodOrder(foodOrder);
			newOrderBean.setValid(true);
		} catch (Exception e) {
			newOrderBean.setValid(false);
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return newOrderBean;
	}

	public static FoodOrdersBean fetchallOrders(FoodOrdersBean foodOrdersBean) {
		ArrayList<FoodOrder> foodOrders = null;
		try {
			Session fetchOrdersSession = FoodzillaSession.openCurrentSession();
			foodOrders = ((ArrayList<FoodOrder>) fetchOrdersSession.createCriteria(FoodOrder.class).list());
			if (foodOrders != null) {
				foodOrdersBean.setFoodOrders(foodOrders);
				foodOrdersBean.setValid(true);
			}
		} catch (Exception e) {
			foodOrdersBean.setValid(false);
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return foodOrdersBean;
	}

	public static OrdersBean fetchallOrderitems(OrdersBean ordersBean) {
		OrderItemsBeans orderItemsBeans = null;
		Vector<OrderItemBean> orderItemBean = null;
		try {
			orderItemsBeans = new OrderItemsBeans();
			Session fetchItemSession = FoodzillaSession.openCurrentSession();
			Criteria criteria = fetchItemSession.createCriteria(Orderitem.class);
			criteria.add(Restrictions.eq("order_id", ordersBean.getOrderId()));
			List<Orderitem> orderitems = criteria.list();
			if (orderitems != null) {
				orderItemBean = new Vector<OrderItemBean>();
				for (Orderitem orderitem : orderitems) {
					OrderItemBean itemBean = new OrderItemBean();
					itemBean.setItemName(orderitem.getName());
					itemBean.setItemPrice(orderitem.getPrice());
					itemBean.setItemDescription(orderitem.getDescription());
					itemBean.setItemQuantity(orderitem.getQuantity());
					itemBean.setItemRemarks(orderitem.getRemarks());
					itemBean.setOrderItemid(orderitem.getOrderitemid());
					orderItemBean.add(itemBean);
				}
				orderItemsBeans.setOrderItems(orderItemBean);
				orderItemsBeans.setValid(true);
				ordersBean.setOrderItems(orderItemsBeans);
			}

		} catch (Exception e) {
			orderItemsBeans.setValid(false);
			ordersBean.setOrderItems(orderItemsBeans);
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return ordersBean;
	}

	public FoodOrdersBean fetchallpendingneworders(FoodOrdersBean foodorderbean, long restaurantid) {
		Session fetchpendingorderSession = null;
		try {
			fetchpendingorderSession = FoodzillaSession.openCurrentSession();
			Criteria fetchpendingcriteria = fetchpendingorderSession.createCriteria(FoodOrder.class);
			fetchpendingcriteria.add(Restrictions.eq("status", foodorderbean.getFoodorderstatus()).ignoreCase());
			fetchpendingcriteria.add(Restrictions.eq("restaurantId", restaurantid));
			ArrayList<FoodOrder> foodOrders = (ArrayList<FoodOrder>) fetchpendingcriteria.list();
			if (foodOrders != null) {
				foodorderbean.setFoodOrders(foodOrders);
				foodorderbean.setValid(true);
			} else {
				foodorderbean.setValid(false);
				foodorderbean.setErrormessage("No New or Pending Orders!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			foodorderbean.setValid(false);
			foodorderbean.setErrormessage(e.getLocalizedMessage());
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return foodorderbean;
	}

	public OrderItemsBeans fetchallorderitemsbyorderid(OrderItemsBeans orderItemsBeans) {
		Session fetchfoodorderitemsession = null;
		try {
			fetchfoodorderitemsession = FoodzillaSession.openCurrentSession();
			Criteria fetchitemCriteria = fetchfoodorderitemsession.createCriteria(Orderitem.class);
			fetchitemCriteria.add(Restrictions.eq("foodOrder", orderItemsBeans.getFoodOrder()));
			ArrayList<Orderitem> orderitems = (ArrayList<Orderitem>) fetchitemCriteria.list();
			if (orderitems != null) {
				orderItemsBeans.setOrderitemlist(orderitems);
				orderItemsBeans.setValid(true);
			} else {
				orderItemsBeans.setValid(false);
				orderItemsBeans.setErrorMessage("Order is Already Delivered");
			}
		} catch (Exception e) {
			e.printStackTrace();
			orderItemsBeans.setErrorMessage(e.getLocalizedMessage());
			orderItemsBeans.setValid(false);
		}
		return orderItemsBeans;
	}

	public OrderItemBean createneworderitem(OrderItemBean orderitembean) {
		Session createneworderitemsession = null;
		try {
			createneworderitemsession = FoodzillaSession.openCurrentSession();
			createneworderitemsession.beginTransaction();
			createneworderitemsession.save(orderitembean.getOrderitem());
			orderitembean.setValid(true);
			createneworderitemsession.getTransaction().commit();
		} catch (Exception e) {
			orderitembean.setValid(false);
			orderitembean.setErrormsg(e.getLocalizedMessage());
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return orderitembean;
	}

	public OrdersBean fetchfoodorderbyid(OrdersBean orderbean, long orderid) {
		Session fetchorderbyidsession = null;
		try {
			fetchorderbyidsession = FoodzillaSession.openCurrentSession();
			FoodOrder foodOrder = (FoodOrder) fetchorderbyidsession.createCriteria(FoodOrder.class).add(Restrictions.eq("orderid", orderid)).uniqueResult();
			orderbean.setFoodOrder(foodOrder);
			orderbean.setValid(true);
		} catch (Exception e) {
			orderbean.setValid(false);
		}finally{
			FoodzillaSession.closeCurrentSession();
		}
		return orderbean;
	}

	public OrdersBean markaorderdelivered(OrdersBean orderbean) {
		Session markorderdeliveredsession = null;
		try {
			markorderdeliveredsession = FoodzillaSession.openCurrentSession();
			markorderdeliveredsession.beginTransaction();
			markorderdeliveredsession.update(orderbean.getFoodOrder());
			markorderdeliveredsession.getTransaction().commit();
			orderbean.setValid(true);
		} catch (Exception e) {
			orderbean.setValid(false);
			orderbean.setErrormsg(e.getLocalizedMessage());
		}finally{
			FoodzillaSession.closeCurrentSession();
		}
		return orderbean;
	}
}
