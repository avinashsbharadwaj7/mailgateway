package com.razorthink.mailgateway.pojo;

public class Message
{
	private Boolean isError;
	private String errorMsg;
	private String successMsg;
	
	public Boolean getIsError()
	{
		return isError;
	}
	public void setIsError( Boolean isError )
	{
		this.isError = isError;
	}
	public String getErrorMsg()
	{
		return errorMsg;
	}
	public void setErrorMsg( String errorMsg )
	{
		this.errorMsg = errorMsg;
	}
	public String getSuccessMsg()
	{
		return successMsg;
	}
	public void setSuccessMsg( String successMsg )
	{
		this.successMsg = successMsg;
	}
	
	
}
