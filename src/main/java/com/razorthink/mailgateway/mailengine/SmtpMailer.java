package com.razorthink.mailgateway.mailengine;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

import com.razorthink.mailgateway.pojo.Configuration;
import com.razorthink.mailgateway.pojo.Mail;

public abstract class SmtpMailer
{
	protected Properties mailServerProps = new Properties();
	protected Authenticator mailAuth = null;
	
	protected String email;
	private String password;
	
	public SmtpMailer (Configuration mailerConfig){
		this.mailServerProps.put( "mail.smtp.user", mailerConfig.getAccEmailId() );
		this.mailServerProps.put( "mail.smtp.host", mailerConfig.getSmtpHost() );
		this.mailServerProps.put( "mail.smtp.port", mailerConfig.getSmtpPort() );
		
		if (mailerConfig.isTlsEnabled() == true){
			this.mailServerProps.put( "mail.smtp.starttls.enable", "true" );
			this.mailServerProps.put( "mail.smtp.auth", "true" );
			
			mailAuth = new SMTPAuthenticator();
			
		}else {
			this.mailServerProps.put( "mail.smtp.starttls.enable", "false" );
			this.mailServerProps.put( "mail.smtp.auth", "false" );
		}
		
		email = mailerConfig.getAccEmailId();
		password = mailerConfig.getAccPassword();
	}
	
	public abstract void sendEmail (Mail confirmMailTemplate) throws MessagingException, UnsupportedEncodingException;
	
	private class SMTPAuthenticator extends Authenticator{
		 public PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(email, password);
		 }
	}
	
}
