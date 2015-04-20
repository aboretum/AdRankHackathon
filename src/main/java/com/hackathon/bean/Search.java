package com.hackathon.bean;

import java.util.*;

public class Search {
	private String searchUser;
	private String searchKeyWord;
	private int searchFrequency;
	private List<AdVideo> videoList;
	
	public String getSearchUser() {
		return searchUser;
	}
	public void setSearchUser(String searchUser) {
		this.searchUser = searchUser;
	}
	public String getSearchKeyWord() {
		return searchKeyWord;
	}
	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}
	public List<AdVideo> getVideoList() {
		return videoList;
	}
	public void setVideoList(List<AdVideo> videoList) {
		this.videoList = videoList;
	}
	public int getSearchFrequency() {
		return searchFrequency;
	}
	public void setSearchFrequency(int searchFrequency) {
		this.searchFrequency = searchFrequency;
	}
}
