package com.auto.service;

import com.auto.entity.UserEntity;

public interface IMessageServiceFacade 
{
	public UserEntity sendCode(String mobile, String code) throws Exception;

}
