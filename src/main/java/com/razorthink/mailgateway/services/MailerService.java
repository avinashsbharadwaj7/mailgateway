package com.razorthink.mailgateway.services;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.razorthink.mailgateway.interfaces.MailerServiceInterface;
import com.razorthink.mailgateway.mailengine.SmtpMailer;
import com.razorthink.mailgateway.mailengine.UserAuthMailSender;
import com.razorthink.mailgateway.mongodb.DataDao;
import com.razorthink.mailgateway.pojo.Configuration;
import com.razorthink.mailgateway.pojo.Mail;
import com.razorthink.mailgateway.pojo.Message;

@Path("/mail")
public class MailerService implements MailerServiceInterface
{
	@POST
	@Path("/sendMail")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message sendMail( Mail emailToSend )
	{
		Message resp = new Message();
		Configuration accConfiguration = DataDao.getConfiguration( emailToSend.getAccName() );
		if (accConfiguration != null){
			try {
				resp.setIsError( false );
				resp.setSuccessMsg( "Mail Sent Successfully" );
				
				SmtpMailer smtpEmailer = new UserAuthMailSender( accConfiguration );
				smtpEmailer.sendEmail( emailToSend );
			} catch (Exception e){
				resp.setIsError( true );
				resp.setErrorMsg( "Error while sending email" );
				
				System.out.println(e.getStackTrace().toString());
				return resp;
			}
		}
		
		else {
			resp.setIsError( true );
			resp.setErrorMsg( "Unable to find config for this account" );
		}
		
		return resp;
	}
	
}