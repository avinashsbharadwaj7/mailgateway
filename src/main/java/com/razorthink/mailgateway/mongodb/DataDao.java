package com.razorthink.mailgateway.mongodb;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.razorthink.mailgateway.pojo.Configuration;

public class DataDao
{
	public static Configuration getConfiguration (String accName){
		Configuration accConfig = null;
		
		DBCollection collection = MongoConnector.getInstance().getDbConnector().getCollection( "account_config" );
		BasicDBObject query = new BasicDBObject("accName", accName);
		
		Cursor cursor = collection.find( query );
		while (cursor.hasNext()){
			Gson gson = new Gson();
			accConfig = gson.fromJson( cursor.next().toString(), Configuration.class );
		}
		
		return accConfig;
	}
	
	public static List<Configuration> getAllConfiguration (){
		List<Configuration> accConfigList = new ArrayList<Configuration>();
		
		DBCollection collection = MongoConnector.getInstance().getDbConnector().getCollection( "account_config" );
		DBCursor configCurssor = collection.find();
		while (configCurssor.hasNext()){
			Gson gson = new Gson();
			accConfigList.add( gson.fromJson( configCurssor.next().toString(), Configuration.class ) );
		}
		
		return accConfigList;
	}
	
	public static void updateConfiguration (Configuration accConfig){
		Gson gson = new Gson();
		String json = gson.toJson( accConfig );
		
		DBObject dbObj = (DBObject) JSON.parse( json );
		DBCollection collection = MongoConnector.getInstance().getDbConnector().getCollection( "account_config" );
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.append( "accName", accConfig.getAccName() );
		collection.update( searchQuery, dbObj );
		
	}
	
	public static void addConfiguration (Configuration accConfig){
		Gson gson = new Gson();
		String json = gson.toJson( accConfig );
		
		DBObject dbObj = (DBObject) JSON.parse( json );
		DBCollection collection = MongoConnector.getInstance().getDbConnector().getCollection( "account_config" );
		
		collection.insert( dbObj );
	}
}
