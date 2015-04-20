package com.hackathon.dao;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.hackathon.util.MessageDigestService;
import com.hackathon.bean.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserDao {
	private String dbCol = "users";
	private DBUtil dbUtil;
	private DBCollection col;
	
	public UserDao(){
		dbUtil = new DBUtil();
		dbUtil.connect();
		col = dbUtil.getCollection(dbCol);
	}

	public List<User> getFrequentUsers() {
		List<User> userList = new ArrayList<User>();
		BasicDBObject gtQuery = new BasicDBObject();
		gtQuery.put("loginFrequency", new BasicDBObject("$gt", 0));
		DBCursor cursor = col.find(gtQuery);
		
		cursor.sort(new BasicDBObject("loginFrequency", -1)).limit(10);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
				User user = new User();
				user.setUserName(doc.get("user_name").toString());
				user.setLoginFrequency((Integer)doc.get("loginFrequency"));
				user.setUserEmail(doc.get("email").toString());
				userList.add(user);
			}
		}finally{
			cursor.close();
		}
		
		return userList;
	}
	
	public void insertNewUser(User user) throws NoSuchAlgorithmException{
		String password = null;
		password = MessageDigestService.getDigest(user.getPassWord());
		
		BasicDBObject doc = new BasicDBObject("user_name",user.getUserName()).
				append("password",password).
				append("email",user.getUserEmail()).
				append("loginFrequency", 1);
		col.insert(doc);
	}

	public User getUserByName(String userName) {
		User user = new User();
		BasicDBObject query = new BasicDBObject("user_name", userName);
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		if(doc==null){
			System.out.println("no such user");
			return null;
		}else{
			String x = doc.get("user_name").toString();
			System.out.println(x);
			
			user.setUserName((String)doc.get("user_name"));
			user.setPassWord((String)doc.get("password"));
			
		}
		
		
		return user;
	}

	public User getUserByEmail(String loginEmail) {
		User user = new User();
		BasicDBObject query = new BasicDBObject("email", loginEmail);
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		if(doc==null){
			System.out.println("email does not exist");
			return null;
		}else{
			user.setUserName((String)doc.get("user_name"));
			user.setPassWord((String)doc.get("password"));
			user.setUserEmail((String)doc.get("email"));
			
		}
		
		return user;
	}

	public void updateUserFrequency(User user) {
		BasicDBObject query = new BasicDBObject("user_name", user.getUserName());
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		int freq = (Integer)doc.get("loginFrequency");
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.put("$set", 
				new BasicDBObject().append("loginFrequency", freq+1));
		col.update(query, updateQuery);
		
	}
	
}
