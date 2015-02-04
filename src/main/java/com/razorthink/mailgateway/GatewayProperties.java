package com.razorthink.mailgateway;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GatewayProperties
{
	private static GatewayProperties instance = null;
	private Properties props = null;
	private GatewayProperties ()
	{
		try
		{
			InputStream propInput = getClass().getClassLoader().getResourceAsStream("mailgateway.properties");
			
			props = new Properties();
			props.load( propInput );
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		
	}
	
	public static GatewayProperties getInstance(){
		if (instance == null){
			instance = new GatewayProperties();
		}
		return instance;
	}
	
	public Properties getProperties(){
		return props;
	}
}
