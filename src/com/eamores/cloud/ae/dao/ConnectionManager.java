package com.eamores.cloud.ae.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	private Connection con;
	private String url;
	private String user;
	private String pass;

	
	public ConnectionManager() {
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config/db.properties"));
		} catch (Exception e) {
			System.out.println("no config: config/db.propertie");
		}

		String db_url = prop.getProperty("db_url").trim();
		String db_port = prop.getProperty("db_port").trim();
		String db_user = prop.getProperty("db_user").trim();
		String db_pass = prop.getProperty("db_pass").trim();
		String db_name = prop.getProperty("db_name").trim();
		
		url = "jdbc:mysql://" + db_url + ":" + db_port + "/" + db_name;
		user = db_user;
		pass = db_pass;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Connection getCon() {
		return con;
	}
	
	public void close() {
		// con.close();
	}



}
