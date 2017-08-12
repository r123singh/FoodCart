package com.foodvilla.register;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.foodvilla.login.UserBean;

public class RegisterDAO {

	static Connection currentCon = null;

	public static UserBean registerUser(UserBean registerUserBean) {
		PreparedStatement preparedStatement = null;
	/*	String query = " insert into login.users (firstname, lastname, username, password, mobile)" + " values (?, ?, ?, ?, ?)";
		try {
			// connect to DB
			currentCon = ConnectionManager.getConnection();
			preparedStatement = currentCon.prepareStatement(query);
			preparedStatement.setString(1, registerUserBean.getFirstName());
			preparedStatement.setString(2, registerUserBean.getLastName());
			preparedStatement.setString(3, registerUserBean.getUsername());
			preparedStatement.setString(4, registerUserBean.getPassword());
			preparedStatement.setString(5, registerUserBean.getMobile());
			preparedStatement.execute();

			registerUserBean.setValid(true);
		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
			registerUserBean.setValid(false);
		}

		// some exception handling
		finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (Exception e) {
				}
				preparedStatement = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}*/

		return registerUserBean;
	}
}
