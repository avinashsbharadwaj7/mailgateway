package com.razorthink.mailgateway.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.razorthink.mailgateway.interfaces.AdminServiceInterface;
import com.razorthink.mailgateway.mongodb.DataDao;
import com.razorthink.mailgateway.pojo.Configuration;
import com.razorthink.mailgateway.pojo.Message;

@Path("/admin")
public class AdminService implements AdminServiceInterface
{
	@GET
	@Path("/getConfiguration")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Configuration> getAllConfiguration()
	{
		return DataDao.getAllConfiguration();
	}
	
	@POST
	@Path("/addConfiguration")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message addConfigurationforAcc( Configuration mailAccConfig )
	{
		Message response = new Message();
		Configuration accConfig = DataDao.getConfiguration( mailAccConfig.getAccName() );
		if (accConfig != null){
			response.setIsError( true );
			response.setErrorMsg( "Configuration for account " + mailAccConfig.getAccName() + " already exists. Try updating the configuration" );
		}
		else {
			DataDao.addConfiguration( mailAccConfig );
			response.setIsError( false );
			response.setSuccessMsg( "Configuration Added Successfully" );
		}
		
		return response;
	}

	@POST
	@Path("/updateConfiguration")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message updateConfigurationForAcc( Configuration mailAccConfig )
	{
		Message response = new Message();
		
		Configuration accConfiguration = DataDao.getConfiguration( mailAccConfig.getAccName() );
		if (accConfiguration != null){
			DataDao.updateConfiguration( mailAccConfig );
			response.setIsError( false );
			response.setSuccessMsg( "Configuration Updated Successfully" );
		}
		else {
			response.setIsError( true );
			response.setErrorMsg( "Configuration for account " + mailAccConfig.getAccName() + " doesn't exist. Try creating the configuration" );
		}
		
		return response;
	}
}
