package com.hackathon.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.hackathon.bean.AdVideo;
import com.hackathon.bean.Search;
import com.hackathon.bean.User;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class SearchDao {
	private String dbCol = "searches";
	private DBUtil dbUtil;
	private DBCollection col;
	
	public SearchDao(){
		dbUtil = new DBUtil();
		dbUtil.connect();
		col = dbUtil.getCollection(dbCol);
	}

	public void insertRecentSearch(User user, List<AdVideo> videoList) {
		Search newSearch = new Search();
		BasicDBObject query = new BasicDBObject("search_user", user.getUserName());
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		//if document for the search is not yet created, initialize it in the database;
		if(doc==null){
			BasicDBObject searchDoc = new BasicDBObject("search_user", user.getUserName());
			col.insert(searchDoc);
		}
		
		cursor=col.find(query);
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		BasicDBObject remove = new BasicDBObject();
		remove.put("$unset", new BasicDBObject("vdList",""));
		col.update(query, remove);
		
		for(AdVideo vd: videoList){
			BasicDBObject push = new BasicDBObject();
			
			BasicDBObject vdObj = new BasicDBObject("vd_title", vd.getTitle()).
					append("vd_views", vd.getTotal_views()).
					append("vd_url",vd.getUrl()).
					append("vd_tmb_url", vd.getThumb_url()).
					append("vd_categoryId", vd.getCategory()).
					append("vd_date", vd.getPublishDate()).
					append("vd_author", vd.getAuthor()).
					append("vd_description",vd.getDescription());
			
			push.put("$push", new BasicDBObject("vdList", vdObj));
			col.update(query, push);
		}
		
	}

	public List<AdVideo> getVideoListBySpecifics(User user,
			final String orderSpecifics) {
		List<AdVideo> newVdList = new ArrayList<AdVideo>();
		
		BasicDBObject query = new BasicDBObject("search_user", user.getUserName());
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		if(doc!=null){
			BasicDBList vdList = (BasicDBList) doc.get("vdList");
			Collections.sort(vdList,new Comparator<Object>(){
				public int compare(Object o, Object o2){
					String s1 = ((BasicDBObject)o).get(orderSpecifics).toString();
					String s2 = ((BasicDBObject)o2).get(orderSpecifics).toString();
					return s1.compareTo(s2);
				}
			});
			
			
			for(Object vdObj:vdList){
				BasicDBObject dbobj = (BasicDBObject)vdObj;
				AdVideo advdo = new AdVideo();
				advdo.setAuthor(dbobj.get("vd_author").toString());
				advdo.setCategory(dbobj.get("vd_categoryId").toString());
				advdo.setPublishDate((Date)dbobj.get("vd_date"));
				advdo.setThumb_url(dbobj.get("vd_tmb_url").toString());
				advdo.setTitle(dbobj.get("vd_title").toString());
				advdo.setTotal_views(dbobj.get("vd_views").toString());
				advdo.setUrl(dbobj.get("vd_url").toString());
				advdo.setDescription(dbobj.get("vd_description").toString());
				newVdList.add(advdo);
			}
			
			
		}
		return newVdList;
	}

	public void insertRecentKeyWord(String keyWord) {
		BasicDBObject query = new BasicDBObject("keyword", keyWord);
		DBCursor cursor = col.find(query);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
			}
		}finally{
			cursor.close();
		}
		
		//if document for the search is not yet created, initialize it in the database;
		if(doc==null){
			BasicDBObject searchDoc = new BasicDBObject("keyword", keyWord).append("frequency", 1);
			col.insert(searchDoc);
			
			return;
		}
		
		int freq = (Integer)doc.get("frequency");
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.put("$set", 
				new BasicDBObject().append("frequency", freq+1));
		col.update(query, updateQuery);
	}

	public List<Search> getSearchListWithFrequency() {
		List<Search> searchList = new ArrayList<Search>();
		BasicDBObject gtQuery = new BasicDBObject();
		gtQuery.put("frequency", new BasicDBObject("$gt", 0));
		DBCursor cursor = col.find(gtQuery);
		
		cursor.sort(new BasicDBObject("frequency", -1)).limit(10);
		DBObject doc = null;
		
		try{
			while(cursor.hasNext()){
				doc = cursor.next();
				Search srch = new Search();
				srch.setSearchFrequency((Integer)doc.get("frequency"));
				srch.setSearchKeyWord(doc.get("keyword").toString());
				searchList.add(srch);
			}
		}finally{
			cursor.close();
		}
		
		return searchList;
	}
}
