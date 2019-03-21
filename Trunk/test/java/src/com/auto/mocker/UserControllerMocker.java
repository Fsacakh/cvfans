package com.auto.mocker;

import org.junit.Test;

import com.app.base.utils.BeanUtils;
import com.app.mock.BaseMocker;
import com.app.mock.MultipartParamRequest;
import com.app.mock.SimpleParamRequest;
import com.auto.entity.UserEntity;

/**
 * 用户相关接口测试类
 * @author zhangsong
 *
 */
public class UserControllerMocker extends BaseMocker
{
	public UserControllerMocker(){}
	
	//用户注册接口测试用例
	public void register() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc, SimpleParamRequest.Method.Post, "/User/Register.action");
		mocker.addParameter("userName","lay1" )
		.addParameter("password", "50511")
		.addParameter("ownerRole", 1)
		.addParameter("ownerId", "09689b096cd542bfa5b03eb2540a51ac");
		
		mocker.call();
	}

	public void login() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc, SimpleParamRequest.Method.Post, "/User/Login.action");
		mocker.addParameter("userName","lay1" )
		.addParameter("password", "50511")
		.addParameter("code", "1121");
		mocker.call();
	}
	
	//用户修改密码测试用例
	public void updatePassword() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc, SimpleParamRequest.Method.Post, "/User/Password/Update.action");
		mocker.addParameter("userId", "f9f9aeb2abfb45dba0d9dc581eedd6d4")
		.addParameter("oldPwd","50511")
		.addParameter("newPwd", "2334545")
		.addParameter("code", "8787");
		mocker.call();
	}
	
	
	//用户修改密码测试用例
	public void authentication() throws Exception
	{
		MultipartParamRequest mocker=new MultipartParamRequest(this.mockMvc, "/MultipartPersist/User/Update.action");
		UserEntity user = new UserEntity();
		user.setUserName("Tony");
		user.setUserId("111");
		mocker.addParameter("User",BeanUtils.writeValueAsString(user));
		
		mocker.call();
	}
	
	//密码重置测试用例
	@Test
	public  void resetPassword() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc, SimpleParamRequest.Method.Post,"/User/Password/Reset.action");
		mocker.addParameter("userId", "f9f9aeb2abfb45dba0d9dc581eedd6d4");
		mocker.call();
	}
}
