package com.razorthink.mailgateway.interfaces;

import java.util.List;

import com.razorthink.mailgateway.pojo.Configuration;
import com.razorthink.mailgateway.pojo.Message;

public interface AdminServiceInterface
{
	public List<Configuration> getAllConfiguration();
	
	public Message addConfigurationforAcc (Configuration mailAccConfig);
	
	public Message updateConfigurationForAcc (Configuration mailAccConfig);
}
