package com.foodvilla.restaurant;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.foodvilla.sess.FoodzillaSession;

public class RestaurantDAO {

	public RestaurantBean createrestaurant(RestaurantBean restaurantBean) {
		try {
			Session newrestaurantcreateSession = FoodzillaSession.openCurrentSession();
			newrestaurantcreateSession.beginTransaction();
			newrestaurantcreateSession.save(restaurantBean.getRestaurant());
			newrestaurantcreateSession.getTransaction().commit();
			restaurantBean.setValid(true);
		} catch (Exception e) {
			restaurantBean.setValid(false);
			restaurantBean.setErrormessage(e.getLocalizedMessage());
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return restaurantBean;
	}

	public Restaurant fetchrestaurantbyid(RestaurantBean restaurantBean, long restaurantid) {
		Session fetchrestaurantsession = null;
		Restaurant restaurant = null;
		try {
			fetchrestaurantsession = FoodzillaSession.openCurrentSession();
			Criteria restaurantcriteria = fetchrestaurantsession.createCriteria(Restaurant.class);
			restaurantcriteria.add(Restrictions.eq("id", restaurantid));
			restaurant = (Restaurant) restaurantcriteria.uniqueResult();
			restaurantBean.setValid(true);
		} catch (Exception e) {
			e.printStackTrace();
			restaurantBean.setValid(false);
			restaurantBean.setErrormessage(e.getLocalizedMessage());
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return restaurant;
	}

	public ArrayList<Restaurant> fetchallrestaurantslist(RestaurantBean restaurantBean) {
		Session fetchallrestaurantsession = null;
		ArrayList<Restaurant> restaurants = null;
		try {
			fetchallrestaurantsession = FoodzillaSession.openCurrentSession();
			Criteria criteria = fetchallrestaurantsession.createCriteria(Restaurant.class);
			restaurants = (ArrayList<Restaurant>) criteria.list();
			restaurantBean.setValid(true);
		} catch (Exception e) {
			restaurantBean.setErrormessage(e.getLocalizedMessage());
			restaurantBean.setValid(false);
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return restaurants;
	}

	public RestaurantBean fetchallrestaurantsbylocation(RestaurantBean restaurantBean) {
		Session restaurantfetchsession = null;
		ArrayList<Restaurant> restaurants = null;
		try {
			restaurantfetchsession = FoodzillaSession.openCurrentSession();
			Criteria criteria = restaurantfetchsession.createCriteria(Restaurant.class);
			criteria.add(Restrictions.ilike("location", restaurantBean.getRestaurantlocation()));
			restaurants = (ArrayList<Restaurant>) criteria.list();
			if (restaurants != null) {
				restaurantBean.setRestaurants(restaurants);
				restaurantBean.setValid(true);
			} else {
				restaurantBean.setValid(false);
				restaurantBean.setErrormessage("We do not Service this location currently");
			}
		} catch (Exception e) {
			restaurantBean.setValid(false);
			restaurantBean.setErrormessage("Server under maintainence");
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return restaurantBean;
	}

	public RestaurantBean fetchrestaurantbyid(RestaurantBean restaurantbean) {
		Session fetchrestaurantsession = null;
		Restaurant restaurant = null;
		try {
			fetchrestaurantsession = FoodzillaSession.openCurrentSession();
			Criteria restaurantcriteria = fetchrestaurantsession.createCriteria(Restaurant.class);
			restaurantcriteria.add(Restrictions.eq("id", restaurantbean.getRestaurantid()));
			restaurant = (Restaurant) restaurantcriteria.uniqueResult();
			restaurantbean.setRestaurant(restaurant);
			restaurantbean.setValid(true);
		} catch (Exception e) {
			e.printStackTrace();
			restaurantbean.setValid(false);
			restaurantbean.setErrormessage("Restaurant Closed!");
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return restaurantbean;
	}

	public RestaurantBean fetchrestaurantbyplaceid(RestaurantBean restaurantbean) {
		Session fetchrestaurantsession = null;
		Restaurant restaurant = null;
		try {
			fetchrestaurantsession = FoodzillaSession.openCurrentSession();
			Criteria restaurantcriteria = fetchrestaurantsession.createCriteria(Restaurant.class);
			restaurantcriteria.add(Restrictions.eq("placeid", restaurantbean.getPlaceid()));
			restaurant = (Restaurant) restaurantcriteria.uniqueResult();
			restaurantbean.setRestaurant(restaurant);
			restaurantbean.setValid(true);
		} catch (Exception e) {
			e.printStackTrace();
			restaurantbean.setValid(false);
			restaurantbean.setErrormessage("Restaurant Closed!");
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return restaurantbean;
	}

	public RestaurantBean updateaddressrestaurantbyplaceid(RestaurantBean restaurantbean) {

		Session updateaddressrestaurantsession = null;
		Restaurant restaurant = null;
		try {
			updateaddressrestaurantsession = FoodzillaSession.openCurrentSession();
			Criteria restaurantcriteria = updateaddressrestaurantsession.createCriteria(Restaurant.class);
			restaurantcriteria.add(Restrictions.eq("placeid", restaurantbean.getPlaceid()));
			restaurant = (Restaurant) restaurantcriteria.uniqueResult();
			restaurant.setAddress(restaurantbean.getRestaurantaddress());
			updateaddressrestaurantsession.beginTransaction();
			updateaddressrestaurantsession.saveOrUpdate(restaurant);
			updateaddressrestaurantsession.getTransaction().commit();
			restaurantbean.setValid(true);
		} catch (Exception e) {
			e.printStackTrace();
			restaurantbean.setValid(false);
			restaurantbean.setErrormessage("Restaurant Closed!");
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return restaurantbean;

	}

	public RestaurantBean saverestaurantimagereferencemap(RestaurantBean restaurantbean) {
		Session updateaddressrestaurantsession = null;
		try {
			updateaddressrestaurantsession = FoodzillaSession.openCurrentSession();
			updateaddressrestaurantsession.beginTransaction();
			updateaddressrestaurantsession.save(restaurantbean.getRestaurantimagemap());
			updateaddressrestaurantsession.getTransaction().commit();
			restaurantbean.setValid(true);
		} catch (Exception e) {
			e.printStackTrace();
			restaurantbean.setValid(false);
			restaurantbean.setErrormessage("Image Closed!");
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return restaurantbean;
	}

	public RestaurantBean fetchallrestaurantslisted(RestaurantBean restaurantbean) {
		Session fetchrestaurantsession = null;
		ArrayList<Restaurant> restaurants = null;
		try {
			fetchrestaurantsession = FoodzillaSession.openCurrentSession();
			Criteria restaurantcriteria = fetchrestaurantsession.createCriteria(Restaurant.class);
			restaurants = (ArrayList<Restaurant>) restaurantcriteria.list();
			restaurantbean.setRestaurants(restaurants);
			restaurantbean.setValid(true);
		} catch (Exception e) {
			e.printStackTrace();
			restaurantbean.setValid(false);
			restaurantbean.setErrormessage("Error Fetching!");
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return restaurantbean;
	}

	public RestaurantImageBean fetchallimagelinksbyplaceid(RestaurantImageBean restaurantbean) {
		Session fetchimagereferencesession = null;
		try {
			fetchimagereferencesession = FoodzillaSession.openCurrentSession();
			Criteria referencecriteria = fetchimagereferencesession.createCriteria(RestaurantImageMap.class).add(Restrictions.eq("restaurantplaceid", restaurantbean.getRestaurantplaceid()));
			ArrayList<RestaurantImageMap> maps = (ArrayList<RestaurantImageMap>) referencecriteria.list();
			restaurantbean.setImageMaps(maps);
			restaurantbean.setValid(true);
		} catch (Exception e) {
			restaurantbean.setErrormessage(e.getLocalizedMessage());
			restaurantbean.setValid(false);
		}
		return restaurantbean;
	}

	public RestaurantBean updaterestaurantimagelink(RestaurantBean restaurantbean) {
		Session updaterestaurantsession = null;
		try {
			updaterestaurantsession = FoodzillaSession.openCurrentSession();
			updaterestaurantsession.beginTransaction();
			updaterestaurantsession.saveOrUpdate(restaurantbean.getRestaurant());
			updaterestaurantsession.getTransaction().commit();
			restaurantbean.setValid(true);
		} catch (Exception e) {
			restaurantbean.setValid(false);
			restaurantbean.setErrormessage(e.getLocalizedMessage());
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return restaurantbean;
	}

	public Restaurant fetchrestaurantbyplaceid(String placeid) {
		Session fetchrestaurantsession = null;
		Restaurant restaurant = null;
		try {
			fetchrestaurantsession = FoodzillaSession.openCurrentSession();
			Criteria criteria = fetchrestaurantsession.createCriteria(Restaurant.class).add(Restrictions.eq("placeid", placeid));
			restaurant = (Restaurant) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return restaurant;
	}

}
