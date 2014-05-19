package com.eamores.cloud.ae.tables;


import java.util.Date;

public class Links {
	private String user_id;
	private String blob_key;
	private String google_url;
	private String amazon_url;
	private Date ts;
	
	public Links(String user_id, String blob_key, String google_url, String amazon_url, Date ts) {
		this.user_id = user_id;
		this.blob_key = blob_key;
		this.google_url = google_url;
		this.amazon_url = amazon_url;
		this.ts = ts;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getBlob_key() {
		return blob_key;
	}

	public void setBlob_key(String blob_key) {
		this.blob_key = blob_key;
	}

	public String getGoogle_url() {
		return google_url;
	}

	public void setGoogle_url(String google_url) {
		this.google_url = google_url;
	}

	public String getAmazon_url() {
		return amazon_url;
	}

	public void setAmazon_url(String amazon_url) {
		this.amazon_url = amazon_url;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}
}
