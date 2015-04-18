package com.hackathon.dao;

import com.mongodb.DBCollection;

public class UserDao {
	private String dbCol = "users";
	private DBUtil dbUtil;
	private DBCollection col;
	
	public UserDao(){
		dbUtil = new DBUtil();
		dbUtil.connect();
		col = dbUtil.getCollection(dbCol);
	}
	
}
