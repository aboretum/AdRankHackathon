package com.hackathon.dao;

import java.util.List;

import com.hackathon.bean.AdVideo;
import com.hackathon.bean.User;
import com.mongodb.DBCollection;

public class VideoDao {
	private String dbCol = "videos";
	private DBUtil dbUtil;
	private DBCollection col;
	
	public VideoDao(){
		dbUtil = new DBUtil();
		dbUtil.connect();
		col = dbUtil.getCollection(dbCol);
	}

	public List<AdVideo> getVideoListBySpecifics(User user,
			String orderSpecifics) {
		
		return null;
	}
	
	
}
