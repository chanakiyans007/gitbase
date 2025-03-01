package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {

	Connection conn;

	public void getDbconnection(String url, String username, String password) throws SQLException {

		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {

		}
	}

	public void getDbconnection() throws SQLException {

		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
		} catch (Exception e) {

		}
	}

	public void closeDbconnection() {
		try {
			conn.close();
		} catch (Exception e) {
		}

	}

	public ResultSet executeSelectQuery(String query) throws Throwable {
		ResultSet result = null;
		try {
			Statement stat = conn.createStatement();
			result = stat.executeQuery(query);
		} catch (Exception e) {

		}
		return result;
	}

	public int executeNonselectQuery(String query) {
		int result = 0;
		try {
			Statement stat = conn.createStatement();
			result = stat.executeUpdate(query);
		} catch (Exception e) {
		}
		return result;
	}

}
