package com.razorthink.mailgateway.pojo;

import java.util.List;

public class Mail
{
	private String accName;
	private String bodyHtml;
	private String bodyText;
	private String subject;
	private List<EmailAddr> toAddr;
	private List<EmailAddr> ccAddr;
	private List<EmailAddr> bccAddr;
	private EmailAddr fromAddr;
	private List<EmailAddr> replyTo;
	
	public String getBodyHtml()
	{
		return bodyHtml;
	}
	public void setBodyHtml( String bodyHtml )
	{
		this.bodyHtml = bodyHtml;
	}
	public String getBodyText()
	{
		return bodyText;
	}
	public void setBodyText( String bodyText )
	{
		this.bodyText = bodyText;
	}
	public String getSubject()
	{
		return subject;
	}
	public void setSubject( String subject )
	{
		this.subject = subject;
	}
	public List<EmailAddr> getToAddr()
	{
		return toAddr;
	}
	public void setToAddr( List<EmailAddr> toAddr )
	{
		this.toAddr = toAddr;
	}
	public List<EmailAddr> getCcAddr()
	{
		return ccAddr;
	}
	public void setCcAddr( List<EmailAddr> ccAddr )
	{
		this.ccAddr = ccAddr;
	}
	public List<EmailAddr> getBccAddr()
	{
		return bccAddr;
	}
	public void setBccAddr( List<EmailAddr> bccAddr )
	{
		this.bccAddr = bccAddr;
	}
	public EmailAddr getFromAddr()
	{
		return fromAddr;
	}
	public void setFromAddr( EmailAddr fromAddr )
	{
		this.fromAddr = fromAddr;
	}
	public List<EmailAddr> getReplyTo()
	{
		return replyTo;
	}
	public void setReplyTo( List<EmailAddr> replyTo )
	{
		this.replyTo = replyTo;
	}
	public String getAccName()
	{
		return accName;
	}
	public void setAccName( String accName )
	{
		this.accName = accName;
	}
	
}
