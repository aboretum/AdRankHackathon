package com.hackathon.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;


/**
 * Database connectivity utility to handle database connections.
 */
public class DBUtil {

    MongoClient mongoClient;
    DB db;
    DBCollection coll;
    
	
	/**
	 * Create a client for database connections.
	 */
    public DBUtil(){
    	try{
    		mongoClient = new MongoClient("localhost", 27017);
    	}catch(Exception e){
    		e.printStackTrace();
    	}    	
    }
    
	/**
	 * Connect to the specified database, if the database is not there, 
	 * it will be created.
	 */
    public void connect() {
    	try {
    		db = mongoClient.getDB("ARdb");
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
    
	/**
	 * Get to a specific collection.
	 */
    public DBCollection getCollection(String collection){
    	DBCollection col = db.getCollection(collection);
    	return col;
    }
    
    
}
