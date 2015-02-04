package com.razorthink.mailgateway.mailengine;

import java.io.UnsupportedEncodingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.razorthink.mailgateway.pojo.Configuration;
import com.razorthink.mailgateway.pojo.EmailAddr;
import com.razorthink.mailgateway.pojo.Mail;

public class UserAuthMailSender extends SmtpMailer
{

	public UserAuthMailSender (Configuration mailerConfig)
	{
		super( mailerConfig );
	}

	public void sendEmail(Mail confirmMailTemplate) throws MessagingException, UnsupportedEncodingException
	{
		String confirmHtml = confirmMailTemplate.getBodyHtml();
		String confirmText = confirmMailTemplate.getBodyText();
		
		
		Session session = null;
		if (super.mailAuth == null){
			session = Session.getInstance( super.mailServerProps );
		}
		
		else {
			session = Session.getInstance( super.mailServerProps, super.mailAuth ); 
		}
		
		MimeMessage confirmationMessage = new MimeMessage( session );
		confirmationMessage.setSubject( confirmMailTemplate.getSubject() );
		confirmationMessage.setFrom( new InternetAddress (confirmMailTemplate.getFromAddr().getEmail(), confirmMailTemplate.getFromAddr().getName() ) );
		
		if (confirmMailTemplate.getToAddr() != null){
			for (EmailAddr emailAddr : confirmMailTemplate.getToAddr()){
				confirmationMessage.addRecipient( Message.RecipientType.TO, new InternetAddress(emailAddr.getEmail(), emailAddr.getName()) );
			}
		}
		
		if (confirmMailTemplate.getCcAddr() != null){
			for (EmailAddr emailAddr : confirmMailTemplate.getCcAddr()){
				confirmationMessage.addRecipient( Message.RecipientType.CC, new InternetAddress(emailAddr.getEmail(), emailAddr.getName()) );
			}
		}
		
		if (confirmMailTemplate.getBccAddr()!=null){
			for (EmailAddr emailAddr : confirmMailTemplate.getBccAddr()){
				confirmationMessage.addRecipient( Message.RecipientType.BCC, new InternetAddress(emailAddr.getEmail(), emailAddr.getName()) );
			}
		}
		
		if (confirmMailTemplate.getReplyTo() != null){
			InternetAddress[] replyToAddr = new InternetAddress[1];
			for (int i = 0; i<confirmMailTemplate.getReplyTo().size(); i++){
				replyToAddr[i] = new InternetAddress( confirmMailTemplate.getReplyTo().get( i ).getEmail(), confirmMailTemplate.getReplyTo().get( i ).getName());
			}
			confirmationMessage.setReplyTo( replyToAddr );
		}
		
		Multipart mpConfirmation = new MimeMultipart("alternative");
		
		if( confirmText.length() > 0 )
		{
			MimeBodyPart textBody = new MimeBodyPart();
			textBody.setContent( confirmText, "text/plain" );
			mpConfirmation.addBodyPart( textBody );
		}
		
		if ( confirmHtml.length() > 0 )
		{
			MimeBodyPart htmlBody = new MimeBodyPart();
			htmlBody.setContent( confirmHtml, "text/html" );
			mpConfirmation.addBodyPart( htmlBody );
		}
		
		confirmationMessage.setContent( mpConfirmation );
		
		Transport.send( confirmationMessage );
	}
}
