package com.eamores.cloud.ae.tables;

public class TotalTags {
	private String tag;
	private int total;
	
	public TotalTags(String tag, int total) {
		this.tag = tag;
		this.total = total;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
