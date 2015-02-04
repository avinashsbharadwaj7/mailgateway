package com.razorthink.mailgateway;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class App
{
	public static void main( String[] args )
	{
		String resourceBasePath = "C:/Users/avinash/workspace/mailgateway/static-content";

		ServletHolder sh = new ServletHolder( ServletContainer.class );
		sh.setInitParameter( "com.sun.jersey.config.property.resourceConfigClass",
				"com.sun.jersey.api.core.PackagesResourceConfig" );
		sh.setInitParameter( "com.sun.jersey.api.json.POJOMappingFeature", "true" );
		sh.setInitParameter( "javax.ws.rs.Application", ConfigClass.class.getCanonicalName() );

		Server server = new Server( Integer.parseInt( GatewayProperties.getInstance().getProperties()
				.getProperty( "port" ) ) );
		ServletContextHandler context = new ServletContextHandler( server, "/rest", ServletContextHandler.NO_SECURITY );
		context.addServlet( sh, "/*" );

		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setDirectoriesListed( true );
		resource_handler.setWelcomeFiles( new String[] { "index.html" } );
		resource_handler.setResourceBase( resourceBasePath );

		List<Handler> handlersList = new ArrayList<Handler>();
		handlersList.add( context );
		handlersList.add( resource_handler );

		Handler[] hdlrs = handlersList.toArray( new Handler[handlersList.size()] );
		HandlerCollection handlerCollection = new HandlerCollection();
		handlerCollection.setHandlers( hdlrs );
		server.setHandler( handlerCollection );

		try
		{
			server.start();
			server.join();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			server.destroy();
		}
	}
}

class ConfigClass extends ResourceConfig
{
	public ConfigClass ()
	{
		packages( "com.razorthink.mailgateway.services" );
		register( JacksonJsonProvider.class );
	}
}