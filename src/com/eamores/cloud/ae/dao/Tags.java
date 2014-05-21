package com.eamores.cloud.ae.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.eamores.cloud.ae.tables.Links;
import com.eamores.cloud.ae.tables.TotalTags;


public class Tags {
	private static ConnectionManager conn = null;
	private static Statement stmt = null;
	private static ResultSet rset = null;

	public static ArrayList<TotalTags> getTags(String uid){
		ArrayList<TotalTags> tags = new ArrayList<TotalTags>();
		
		String query = "SELECT DISTINCT tag, count(tag) as total FROM Tags WHERE user_id = '" + uid + "' group by tag";
		
		
		//Select   from Tags group by tag
		
		try {
			conn = new ConnectionManager();
			stmt = conn.getCon().createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				String tag = rset.getString("tag");
				int total = rset.getInt("total");
				
				tags.add(new TotalTags(tag, total));
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
		
		return tags;
	}
	
	public static ArrayList<Links> getPics(String uid, String tag){
		ArrayList<Links> links = new ArrayList<Links>();
		
		//select Blobs.user_id as user_id, Blobs.blob_key as blob_key, google_url, amazon_url from Blobs, Tags where Blobs.blob_key = Tags.blob_key and Tags.user_id ='104527062163568148406' and tag='Face';
		
		String query = "SELECT DISTINCT Blobs.user_id as user_id, Blobs.blob_key as blob_key, google_url, amazon_url, ts "
				+ " FROM Blobs, Tags WHERE Blobs.blob_key = Tags.blob_key and Tags.user_id = '" + uid + "' and tag='" + tag + "'";		
				
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
}
