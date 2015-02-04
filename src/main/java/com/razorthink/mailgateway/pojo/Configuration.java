package com.razorthink.mailgateway.pojo;

public class Configuration
{
	private String accName;
	private String accEmailId;
	private String smtpHost;
	private String smtpPort;
	private Boolean tlsEnabled;
	private String accPassword;
	
	
	public String getSmtpHost()
	{
		return smtpHost;
	}
	public void setSmtpHost( String smtpHost )
	{
		this.smtpHost = smtpHost;
	}
	public String getSmtpPort()
	{
		return smtpPort;
	}
	public void setSmtpPort( String smtpPort )
	{
		this.smtpPort = smtpPort;
	}
	public Boolean isTlsEnabled()
	{
		return tlsEnabled;
	}
	public void setTlsEnabled( Boolean isTlsEnabled )
	{
		this.tlsEnabled = isTlsEnabled;
	}
	public String getAccEmailId()
	{
		return accEmailId;
	}
	public void setAccEmailId( String accEmailId )
	{
		this.accEmailId = accEmailId;
	}
	public String getAccName()
	{
		return accName;
	}
	public void setAccName( String accName )
	{
		this.accName = accName;
	}
	public String getAccPassword()
	{
		return accPassword;
	}
	public void setAccPassword( String accPassword )
	{
		this.accPassword = accPassword;
	}
	
	
}
