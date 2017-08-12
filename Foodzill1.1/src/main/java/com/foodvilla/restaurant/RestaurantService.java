package com.foodvilla.restaurant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.foodvilla.sess.PageDAO;
import com.foodvilla.sess.Pagetoken;

public class RestaurantService {

	public static RestaurantBean createnewrestaurantobject(RestaurantBean restaurantBean) {
		RestaurantDAO restaurantdao = null;
		Restaurant restaurant = null;
		try {
			restaurantdao = new RestaurantDAO();
			restaurant = new Restaurant();
			restaurant.setAddress(restaurantBean.getRestaurantaddress());
			restaurant.setFoodcategory(restaurantBean.getRestaurantfoodcategory());
			restaurant.setCreated(new Date(System.currentTimeMillis()));
			restaurant.setDeliverycharges(Double.parseDouble(restaurantBean.getRestaurantdeliverycharges()));
			restaurant.setEsitmatedtime(Integer.parseInt(restaurantBean.getRestaurantestimateddeliverytime()));
			restaurant.setLocation(restaurantBean.getRestaurantlocation());
			restaurant.setMinimumordercharges(Double.parseDouble(restaurantBean.getRestaurantminimumordercharges()));
			restaurant.setName(restaurantBean.getRestaurantname());
			restaurant.setPaymentmode(restaurantBean.getRestaurantpaymentmode());
			restaurant.setFoodcategory(restaurantBean.getRestaurantfoodcategory());
			restaurantBean.setRestaurant(restaurant);
			restaurantBean = restaurantdao.createrestaurant(restaurantBean);
		} catch (Exception e) {
			restaurantBean.setValid(false);
			restaurantBean.setErrormessage(e.getLocalizedMessage());
		}

		return restaurantBean;
	}

	public static RestaurantBean fetchrestaurantbyrestaurantid(RestaurantBean restaurantBean) {
		RestaurantDAO restaurantDAO = null;
		try {
			long restaurantid = restaurantBean.getRestaurantid();
			restaurantDAO = new RestaurantDAO();
			Restaurant restaurant = restaurantDAO.fetchrestaurantbyid(restaurantBean, restaurantid);
			if (restaurant != null) {
				restaurantBean.setRestaurant(restaurant);
				restaurantBean.setValid(true);
			} else {
				restaurantBean.setValid(false);
				restaurantBean.setErrormessage("We do not Service this locations");
			}
		} catch (Exception e) {
			restaurantBean.setValid(false);
			restaurantBean.setErrormessage(e.getLocalizedMessage());
		}
		return restaurantBean;
	}

	public static ArrayList<Restaurant> fetchallrestaurantlist() {
		ArrayList<Restaurant> restaurants = null;
		RestaurantDAO restaurantDAO = null;
		try {
			RestaurantBean restaurantBean = new RestaurantBean();
			restaurantBean.setValid(false);
			restaurantDAO = new RestaurantDAO();
			restaurants = restaurantDAO.fetchallrestaurantslist(restaurantBean);
		} catch (Exception e) {
		}
		return restaurants;
	}

	public static RestaurantBean fetchallrestaurantsbylocation(RestaurantBean restaurantBean) {
		RestaurantDAO restaurantDAO = null;
		try {
			restaurantDAO = new RestaurantDAO();
			restaurantBean = restaurantDAO.fetchallrestaurantsbylocation(restaurantBean);
			if (restaurantBean.isValid()) {
				JSONArray restaurantjsonArray = new JSONArray();
				ArrayList<Restaurant> restaurants = restaurantBean.getRestaurants();
				for (Restaurant restaurant : restaurants) {
					JSONObject restaurantJsonObject = new JSONObject();
					restaurantJsonObject.put("id", restaurant.getId());
					restaurantJsonObject.put("name", restaurant.getName());
					restaurantJsonObject.put("minimum_order", restaurant.getMinimumordercharges());
					restaurantJsonObject.put("delivery_mode", restaurant.getDeliverymode());
					restaurantJsonObject.put("delivery_charge", restaurant.getDeliverycharges());
					restaurantJsonObject.put("address", restaurant.getAddress());
					restaurantJsonObject.put("estimated_delivery", restaurant.getEsitmatedtime());
					restaurantJsonObject.put("payment_mode", restaurant.getPaymentmode());
					restaurantjsonArray.put(restaurantJsonObject);
				}
				JSONObject restaurantjson = new JSONObject();
				restaurantjson.put("restaurants", restaurantjsonArray);
				restaurantjson.put("location", restaurantBean.getRestaurantlocation());
				restaurantBean.setRestaurantJson(restaurantjson);
			}

		} catch (Exception e) {
			restaurantBean.setValid(false);
			restaurantBean.setErrormessage(e.getLocalizedMessage());
		}
		return restaurantBean;
	}

	private static String getmapsyncapiresponse(RestaurantBean bean) {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		StringBuilder builder = null;
		String placesurl = null;
		String token = bean.getPagetoken();
		if (token != null && !token.isEmpty()) {
			placesurl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?type=restaurant&location=" + bean.getRestaurantlocation()
					+ "&rankby=distance&key=AIzaSyATo3r84gKzn05B7lgBzCn61Om-G_M90IA&pagetoken=" + token;
		} else {
			placesurl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?type=restaurant&location=" + bean.getRestaurantlocation()
					+ "&rankby=distance&key=AIzaSyATo3r84gKzn05B7lgBzCn61Om-G_M90IA";
		}
		try {
			Logger.getAnonymousLogger().log(Level.ALL, placesurl);
			URL url = new URL(placesurl);
			connection = (HttpURLConnection) url.openConnection();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			builder = new StringBuilder();
			String readline = null;
			while ((readline = reader.readLine()) != null) {
				builder.append(readline);
			}
		} catch (Exception e) {
			bean.setErrormessage(e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					bean.setErrormessage(e.getLocalizedMessage());
					e.printStackTrace();
				}
				if (connection != null) {
					connection.disconnect();
				}
			}

		}
		return builder.toString();
	}

	private static String getthecurrentlocationofuser() {

		HttpURLConnection connection = null;
		BufferedReader reader = null;
		StringBuilder builder = null;
		try {
			URL url = new URL("https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyATo3r84gKzn05B7lgBzCn61Om-G_M90IA");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(HttpMethod.POST);
			connection.setRequestProperty("Content-Type", "application/json");
			JSONArray wifiaccesspoints = new JSONArray();
			JSONObject macjson = new JSONObject();
			macjson.put("macAddress", "E4-F8-9C-ED-9C-ED");
			wifiaccesspoints.put(macjson);
			JSONObject request = new JSONObject();
			request.put("wifiAccessPoints", wifiaccesspoints);
			OutputStreamWriter oos = new OutputStreamWriter(connection.getOutputStream());
			oos.write(request.toString());
			oos.flush();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			builder = new StringBuilder();
			String readline = null;
			while ((readline = reader.readLine()) != null) {
				builder.append(readline);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (connection != null) {
					connection.disconnect();
				}
			}

		}
		return builder.toString();
	}

	public static RestaurantBean syncmapapirestaurants(RestaurantBean restaurantbean) {
		RestaurantDAO restaurantdao = null;
		PageDAO pagedao = null;
		String response = getmapsyncapiresponse(restaurantbean);
		try {
			if (response != null) {
				restaurantdao = new RestaurantDAO();
				JSONObject responsejson = new JSONObject(response);
				if (responsejson.getString("status").equalsIgnoreCase("OK")) {
					if (responsejson.has("results")) {
						JSONArray results = responsejson.getJSONArray("results");
						for (int i = 0; i < results.length(); i++) {
							JSONObject restaurantjson = results.getJSONObject(i);
							Restaurant verifyrestaurant = restaurantdao.fetchrestaurantbyplaceid(restaurantjson.getString("place_id"));
							if (verifyrestaurant == null) {
								Restaurant restaurant = new Restaurant();
								restaurant.setName(restaurantjson.getString("name"));
								if (restaurantjson.has("open_now")) {
									restaurant.setOpennow(String.valueOf(restaurantjson.getBoolean("open_now")));
								} else {
									if (restaurantjson.has("opening_hours")) {
										restaurant.setOpennow(String.valueOf(restaurantjson.getJSONObject("opening_hours").getBoolean("open_now")));
									} else {
										restaurant.setOpennow(String.valueOf(Boolean.FALSE));
									}
								}
								if (restaurantjson.has("geometry")) {
									double lat = restaurantjson.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
									double lng = restaurantjson.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
									restaurant.setLocation(lat + "," + lng);
								}

								if (restaurantjson.has("vicinity")) {
									restaurant.setAddress(restaurantjson.getString("vicinity"));
								}
								restaurant.setDeliverymode("Home Delivery");
								restaurant.setPaymentmode("COD");
								if (restaurantjson.has("rating")) {
									restaurant.setRating(String.valueOf(restaurantjson.getDouble("rating")));
								}
								restaurant.setPlaceid(restaurantjson.getString("place_id"));
								restaurantbean.setRestaurant(restaurant);
								restaurantdao.createrestaurant(restaurantbean);
							}
						}
					}
					if (responsejson.has("next_page_token")) {
						pagedao = new PageDAO();
						restaurantbean.setPagetoken(responsejson.getString("next_page_token"));
						pagedao.pushnextpagetoken(responsejson.getString("next_page_token"));
					}
					restaurantbean.setValid(true);
				} else {
					restaurantbean.setValid(true);
					restaurantbean.setErrormessage("PAGE_END");
				}
			} else {
				restaurantbean.setValid(true);
				restaurantbean.setErrormessage("PAGE_END");
			}
		} catch (Exception e) {
			restaurantbean.setValid(false);
			restaurantbean.setErrormessage(e.getLocalizedMessage());
			e.printStackTrace();
		}

		return restaurantbean;
	}

	public static RestaurantBean fetchrestaurantimageurls(RestaurantBean restaurantbean) {
		String placedetailurl = "https://maps.googleapis.com/maps/api/place/details/json?key=AIzaSyATo3r84gKzn05B7lgBzCn61Om-G_M90IA&placeid=" + restaurantbean.getPlaceid();
		RestaurantDAO restaurantdao = null;
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		StringBuilder builder = null;
		try {
			builder = new StringBuilder();
			restaurantdao = new RestaurantDAO();
			URL placedetail = new URL(placedetailurl);
			conn = (HttpURLConnection) placedetail.openConnection();
			conn.setRequestMethod("GET");
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			String response = builder.toString();
			if (response != null) {
				JSONObject responsejson = new JSONObject(response);
				if (responsejson.getString("status").equalsIgnoreCase("OK")) {
					responsejson = responsejson.getJSONObject("result");
					if (responsejson.has("formatted_address")) {
						restaurantbean.setRestaurantaddress(responsejson.getString("formatted_address"));
						restaurantbean = restaurantdao.updateaddressrestaurantbyplaceid(restaurantbean);
					}
					if (responsejson.has("photos")) {
						JSONArray imagejsonarray = responsejson.getJSONArray("photos");
						for (int i = 0; i < imagejsonarray.length(); i++) {
							JSONObject imagejson = imagejsonarray.getJSONObject(i);
							RestaurantImageMap restaurantimagemap = new RestaurantImageMap();
							if (imagejson.has("photo_reference")) {
								restaurantimagemap.setImagereference(imagejson.getString("photo_reference"));
								restaurantimagemap.setRestaurantplaceid(restaurantbean.getPlaceid());
								restaurantbean.setRestaurantimagemap(restaurantimagemap);
								restaurantbean = restaurantdao.saverestaurantimagereferencemap(restaurantbean);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			restaurantbean.setErrormessage(e.getLocalizedMessage());
			restaurantbean.setValid(false);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return restaurantbean;
	}

	public static RestaurantBean fetchallrestaurantslisted(RestaurantBean restaurantbean) {
		RestaurantDAO restaurantdao = null;
		PageDAO pagedao = null;
		try {
			pagedao = new PageDAO();
			Pagetoken pagetoken = pagedao.fetchentitypagetoken();
			if (pagetoken != null) {
				restaurantbean.setPagetoken(pagetoken.getToken());
			}
			restaurantdao = new RestaurantDAO();
			restaurantbean = restaurantdao.fetchallrestaurantslisted(restaurantbean);
			if (restaurantbean.getRestaurants() != null) {
				restaurantbean.setValid(true);
			} else {
				restaurantbean.setValid(false);
				restaurantbean.setErrormessage("Error fetching restaurants");
			}
		} catch (Exception e) {
			restaurantbean.setValid(false);
		}
		return restaurantbean;
	}

	public static RestaurantImageBean fetchallimagelinksbyplaceid(RestaurantImageBean restaurantbean) {
		RestaurantDAO restaurantdao = null;
		try {
			restaurantdao = new RestaurantDAO();
			restaurantbean = restaurantdao.fetchallimagelinksbyplaceid(restaurantbean);
			if (restaurantbean.getImageMaps() != null) {
				restaurantbean.setValid(true);
			} else {
				restaurantbean.setValid(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			restaurantbean.setErrormessage(e.getLocalizedMessage());
			restaurantbean.setValid(false);
		}
		return restaurantbean;
	}

	public static RestaurantImageBean fetchimagestaticurllocationsbyreference(RestaurantImageBean imagebean) {
		String imagelink = "https://maps.googleapis.com/maps/api/place/photo?key=AIzaSyATo3r84gKzn05B7lgBzCn61Om-G_M90IA&maxwidth=200&photoreference=CmRaAAAAKJ8bJInXVH0VjTMkeW_n9BhY546ksbLnzeSWAB1ckquIilMV6--6t2I8--_6-4ssbE8iP7yR24y-VRTkuxVs9fGPlGP1IUqli8AHH32T1GA1GYkLaVa7FwYI99Kzt6v0EhBFHY02PjVJkHs7inqqE_UmGhREizXuJxXI-7CfYd47ib_u4WSkfg&placeid=ChIJ12c8LaB-UDcRk-AsvHYUZ5Y";
		HttpURLConnection conn = null;
		try {
			URL imagecontenturl = new URL(imagelink);
			conn = (HttpURLConnection) imagecontenturl.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application");
			conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			String imagelocation = conn.getHeaderField("Location");
			imagebean.setStaticimageurl(imagelocation);
			imagebean.setValid(true);
		} catch (Exception e) {
			imagebean.setErrormessage(e.getLocalizedMessage());
			imagebean.setValid(false);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return imagebean;
	}

	public static RestaurantBean updaterestaurantimagelinks(RestaurantBean restaurantbean) {
		RestaurantDAO restaurantdao = null;
		try {
			restaurantdao = new RestaurantDAO();
			restaurantbean = restaurantdao.fetchrestaurantbyplaceid(restaurantbean);
			if (restaurantbean.getRestaurant() != null) {
				Restaurant restaurant = restaurantbean.getRestaurant();
				restaurant.setPlaceimageurl(restaurantbean.getRestaurantdisplayimage());
				restaurantbean.setRestaurant(restaurant);
				restaurantbean = restaurantdao.updaterestaurantimagelink(restaurantbean);
			} else {
				restaurantbean.setValid(false);
				restaurantbean.setErrormessage("No restaurant");
			}
		} catch (Exception e) {
			restaurantbean.setErrormessage(e.getLocalizedMessage());
			restaurantbean.setValid(false);
		}
		return restaurantbean;
	}

}
