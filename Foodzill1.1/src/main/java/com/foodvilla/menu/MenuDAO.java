package com.foodvilla.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.foodvilla.restaurant.Restaurant;
import com.foodvilla.sess.FoodzillaSession;

public class MenuDAO {

	public static Vector<MenuItemBean> getMenuBean() {
		Vector<MenuItemBean> menuItemBeans = new Vector<MenuItemBean>();
		return menuItemBeans;

	}

	public MenuItemBean createnewmenuitem(MenuItemBean menuItemBean) {
		Session createmenuitemsession = null;
		try {
			createmenuitemsession = FoodzillaSession.openCurrentSession();
			createmenuitemsession.beginTransaction();
			createmenuitemsession.save(menuItemBean.getMenuitem());
			createmenuitemsession.getTransaction().commit();
			menuItemBean.setValid(true);
		} catch (Exception e) {
			e.printStackTrace();
			menuItemBean.setValid(false);
			menuItemBean.setErrormessage(e.getLocalizedMessage());
		}
		return menuItemBean;
	}

	public MenuItemBean fetchallmenuCategorybyrestaurant(MenuItemBean menuItemBean, Restaurant restaurant) {
		Session fetchmenuitemssession = null;
		try {
			fetchmenuitemssession = FoodzillaSession.openCurrentSession();
			ProjectionList projectionlist = Projections.projectionList();
			projectionlist.add(Projections.distinct(Projections.property("category"))).add(Projections.property("categoryimage"));
			Criteria criteria = fetchmenuitemssession.createCriteria(MenuItem.class).add(Restrictions.eq("restaurant", restaurant)).setProjection(projectionlist);
			List<Object> categoryitems = criteria.list();
			if (categoryitems != null) {
				HashMap propertymap = new HashMap();
				for (Object categoryitem : categoryitems) {
					Object[] categoryset = (Object[]) categoryitem;
					propertymap.put((String) categoryset[0], (String) categoryset[1]);
				}
				menuItemBean.setPropertymap(propertymap);
				menuItemBean.setValid(true);
			} else {
				menuItemBean.setValid(false);
			}
		} catch (Exception e) {
			menuItemBean.setErrormessage(e.getLocalizedMessage());
			menuItemBean.setValid(false);
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return menuItemBean;
	}
	public MenuItemBean fetchallmenuitemsbycategory(MenuItemBean menuitembean, Restaurant restaurant) {
		Session fetchmenuitemsession = null;
		try {
			fetchmenuitemsession = FoodzillaSession.openCurrentSession();
			Criteria criteria = fetchmenuitemsession.createCriteria(MenuItem.class);
			ArrayList<MenuItem> menuitems = (ArrayList<MenuItem>) criteria.add(Restrictions.eq("category", menuitembean.getMenuitemcategory())).add(Restrictions.eq("restaurant", restaurant)).list();
			if (menuitems != null) {
				menuitembean.setMenuItems(menuitems);
				menuitembean.setValid(true);
			} else {
				menuitembean.setValid(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			menuitembean.setValid(false);
			menuitembean.setErrormessage(e.getLocalizedMessage());
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return menuitembean;
	}

}
