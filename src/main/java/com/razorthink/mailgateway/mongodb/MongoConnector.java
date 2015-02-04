package com.razorthink.mailgateway.mongodb;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.razorthink.mailgateway.GatewayProperties;

public class MongoConnector
{
	private static MongoConnector instance = null;
	private MongoClient mongoClient = null;
	
	private MongoConnector ()
	{
		try
		{
			mongoClient = new MongoClient( GatewayProperties.getInstance().getProperties().getProperty( "mongo_server_address" ) );
		}
		catch( UnknownHostException e )
		{
			e.printStackTrace();
		}
	}
	
	public static MongoConnector getInstance (){
		if (instance == null){
			instance = new MongoConnector();
		}
		return instance;
	}
	
	public DB getDbConnector (){
		DB databaseConn = mongoClient.getDB( GatewayProperties.getInstance().getProperties().getProperty( "mongo_dbname" ) );
		return databaseConn;
	}
}
