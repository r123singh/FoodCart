package com.foodvilla.login;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.foodvilla.sess.FoodzillaSession;

public class UserDAO {

	public static UserBean login(UserBean bean) {

		Session userLoginSession=FoodzillaSession.openCurrentSession();
		Criteria criteria = userLoginSession.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", bean.getUsername()));
		criteria.setMaxResults(1);
		User user = (User) criteria.uniqueResult();
		if (user != null) {
			if (user.getPassword().trim().equals(bean.getPassword().trim())) {
				bean.setValid(true);
			} else {
				bean.setValid(false);
			}
		} else {
			bean.setValid(false);
		}
		FoodzillaSession.closeCurrentSession();
		return bean;
	}
}
