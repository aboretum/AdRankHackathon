package com.hackathon.bean;

public class Video {
	private String title;
	private String url;
	private String views_per_day;
	private String category;
	private String total_views;
	private String author;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getViews_per_day() {
		return views_per_day;
	}
	public void setViews_per_day(String views_per_day) {
		this.views_per_day = views_per_day;
	}
	public String getTotal_views() {
		return total_views;
	}
	public void setTotal_views(String total_views) {
		this.total_views = total_views;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
