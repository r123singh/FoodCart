package com.foodvilla.sess;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class PageDAO {

	public Pagetoken fetchentitypagetoken() {
		Session fetchtokenitemssession = null;
		Pagetoken pagetoken = null;
		try {
			fetchtokenitemssession = FoodzillaSession.openCurrentSession();
			Criteria criteria = fetchtokenitemssession.createCriteria(Pagetoken.class).add(Restrictions.eq("entity", "restaurant"));
			pagetoken = (Pagetoken) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
		return pagetoken;
	}

	public void pushnextpagetoken(String nexttoken) {
		Session updatenextpagetokensession = null;
		try {
			updatenextpagetokensession = FoodzillaSession.openCurrentSession();
			Criteria criteria = updatenextpagetokensession.createCriteria(Pagetoken.class).add(Restrictions.eq("entity", "restaurant"));
			Pagetoken token = (Pagetoken) criteria.uniqueResult();
			token.setToken(nexttoken);
			updatenextpagetokensession.beginTransaction();
			updatenextpagetokensession.saveOrUpdate(token);
			updatenextpagetokensession.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FoodzillaSession.closeCurrentSession();
		}
	}

}
