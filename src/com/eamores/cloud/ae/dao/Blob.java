package com.eamores.cloud.ae.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.eamores.cloud.ae.tables.Links;
import com.eamores.cloud.ae.tables.PicSummary;


public class Blob {
	private static ConnectionManager conn = null;
	private static Statement stmt = null;
	private static ResultSet rset = null;


	public static int add(String user_id, String blob_key,  String imageUrl) {
		int updates = 0;
		
		String query = "INSERT INTO Blobs (user_id, blob_key, google_url)";
		query += "VALUES('" + user_id + "', '" + blob_key + "', '" + imageUrl + "')" ;
		

		try {
			conn = new ConnectionManager();
			stmt = conn.getCon().createStatement();
			updates = stmt.executeUpdate(query);

			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ex) {
			System.out.println("Sql exception: " + ex);
		}
		
		return updates;
	}
	
	public static ArrayList<Links> getLinks(String uid){
		ArrayList<Links> links = new ArrayList<Links>();
		
		String query = "SELECT user_id, blob_key, google_url, amazon_url, ts FROM Blobs WHERE user_id = '" + uid + "'";
		
		try {
			conn = new ConnectionManager();
			stmt = conn.getCon().createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				String user_id = rset.getString("user_id");
				String blob_key = rset.getString("blob_key");
				String google_url = rset.getString("google_url");
				String amazon_url = rset.getString("amazon_url");
				Date ts = rset.getTimestamp("ts");
				
				links.add(new Links(user_id, blob_key, google_url, amazon_url, ts));
			}
			
			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return links;
	}
	
	public static PicSummary getPics(String blob_key){
		PicSummary pics = null;
		
		String query = "SELECT DISTINCT google_url, amazon_url FROM Blobs WHERE blob_key = '" + blob_key + "'";
		
		try {
			conn = new ConnectionManager();
			stmt = conn.getCon().createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				String google_url = rset.getString("google_url");
				String amazon_url = rset.getString("amazon_url");
				
				pics = new PicSummary(blob_key, google_url, amazon_url);
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		query = "SELECT DISTINCT tag FROM Tags WHERE blob_key = '" + blob_key + "'";
		
		try {
			conn = new ConnectionManager();
			stmt = conn.getCon().createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				String tag = rset.getString("tag");
				
				pics.getTags().add(tag);
			}
			
			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return pics;
	}


}
