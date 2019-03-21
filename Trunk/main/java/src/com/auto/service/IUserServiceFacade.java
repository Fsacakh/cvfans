package com.auto.service;

import com.auto.entity.DeviceVersionEntity;
import com.auto.entity.UserEntity;

public interface IUserServiceFacade {
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public UserEntity login(String userName, String password) throws Exception;
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public UserEntity register(String userId,String mobileNo, String userName, String password, int role, String introMobileNo, String nickName) throws Exception;
	
	/**
	 * 修改密码接口
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @throws Exception
	 */
	public void updatePassword(String userId, String oldPwd, String newPwd) throws Exception;
	
	/**
	 * 密码重置接口
	 * @param userId
	 * @throws Exception
	 */
	public void resetPassword(String userId,String password) throws Exception;
}
