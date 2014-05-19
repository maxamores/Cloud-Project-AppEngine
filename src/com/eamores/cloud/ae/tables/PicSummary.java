package com.eamores.cloud.ae.tables;

import java.util.ArrayList;

public class PicSummary {
	private String blob_key;
	private String google_url;
	private String amazon_url;
	private ArrayList<String> tags;
	
	public PicSummary(String blob_key, String google_url, String amazon_url) {
		super();
		this.blob_key = blob_key;
		this.google_url = google_url;
		this.amazon_url = amazon_url;
		this.tags = new ArrayList<String>();
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

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
}
