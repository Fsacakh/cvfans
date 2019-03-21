package com.auto.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.base.common.AppException;
import com.app.base.utils.StringUtils;
import com.app.base.web.JsonParameter;
import com.app.base.web.SessionParameter;
import com.app.base.web.common.DataFormResponse;
import com.app.base.web.common.MessageResponse;
import com.app.base.web.common.ResponseCode;
import com.app.base.web.controller.BaseController;
import com.auto.common.impl.ISessionManager;
import com.auto.entity.UserEntity;
import com.auto.service.IUserServiceFacade;

@Controller
@RequestMapping("/User")
public class UserController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "UserServiceFacade")
	private IUserServiceFacade serviceFacade;
	
	public UserController() {
	}
	
	/**
	 * 登录请求接入
	 * @param userName 账号
	 * @param password 密码
	 * @param sessionCode 缓存的验证码
	 * @param code 输入的验证码
	 * @return
	 */
	@RequestMapping(value="/Login.*")
	public @ResponseBody DataFormResponse<UserEntity> login(@JsonParameter("userName") String userName,  @JsonParameter("password") String password, @SessionParameter("RANDOMVALIDATECODEKEY") String sessionCode,  @JsonParameter("code") String code, @JsonParameter("source") String source, HttpSession httpSession){
		DataFormResponse<UserEntity> response = null;
		try{
			if(source != null && ! sessionCode.equals(code)){
				throw new AppException("验证码错误，请重新输入");
			}
			
			UserEntity user = serviceFacade.login(userName, password);
			httpSession.setAttribute(ISessionManager.USER_KEY, user);
			
			response = new DataFormResponse<UserEntity>("登录成功。", user);
		}catch(Exception exc){
			logger.error(StringUtils.format("Login Exception for userName=[{0}], password=[{1}].", new Object[]{userName, password}), exc);
			response = new DataFormResponse<UserEntity>(exc);
		}
		
		return response;
	}
	
	/**
	 * 注销请求接入
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/Logout.*")
	public String logout(HttpServletRequest request, HttpSession session){
		session.invalidate();
		return "redirect:" + "/index.html"; 
	}
	
	/**
	 * 用户注册请求接入
	 * @param userId
	 * @param userName
	 * @param password
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/Register.*")
	public @ResponseBody DataFormResponse<UserEntity> register( @JsonParameter("userId") String userId,@JsonParameter("mobileNo") String mobileNo,  @JsonParameter("userName") String userName,  @JsonParameter("password") String password,  @JsonParameter("ownerRole") Integer ownerRole, @JsonParameter("intro_mobile_no") String introMobileNo, @JsonParameter("nickName") String nickName){
		DataFormResponse<UserEntity> response = null;
		
		try{
			UserEntity user = serviceFacade.register(userId,mobileNo, userName, password, ownerRole, introMobileNo, nickName);
			
			response = new DataFormResponse<UserEntity>("用户注册成功。", user);
		}catch(Exception exc){
			logger.error("Register Exception.", exc);
			response = new DataFormResponse<UserEntity>(exc);
		}
		
		return response;
	}
	
	/**
	 * 密码修改请求接入
	 * @param user
	 * @param oldPwd`
	 * @param newPwd
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/Password/Update.*")
	public @ResponseBody MessageResponse updatePassword(@JsonParameter("userId") String userId, @JsonParameter("oldPwd") String oldPwd, @JsonParameter("newPwd") String newPwd,@JsonParameter("code") String code){
		MessageResponse response = null;
		
		try{
			serviceFacade.updatePassword(userId, oldPwd, newPwd);
			
			response = new MessageResponse(ResponseCode.Success, "密码修改成功.");
		}catch(Exception exc){
			logger.error("Register Exception.", exc);
			response = new MessageResponse(exc);
		}
		
		return response;
	}
	
	/**
	 * 登录重置请求接入
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/Password/Reset.*")
	public @ResponseBody MessageResponse resetPassword(@JsonParameter("userId") String userId,@JsonParameter("password") String password){
		MessageResponse response = null;
		
		try{
			serviceFacade.resetPassword(userId,password);			
			
			response = new MessageResponse(ResponseCode.Success, "密码重置成功.");
		}catch(Exception exc){
			logger.error("Register Exception.", exc);
			response = new MessageResponse(exc);
		}
		
		return response;
	}
}