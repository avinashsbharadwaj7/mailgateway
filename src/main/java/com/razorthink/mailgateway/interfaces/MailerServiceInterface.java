package com.razorthink.mailgateway.interfaces;

import com.razorthink.mailgateway.pojo.Mail;
import com.razorthink.mailgateway.pojo.Message;

public interface MailerServiceInterface
{
	public Message sendMail(Mail emailToSend);
}
