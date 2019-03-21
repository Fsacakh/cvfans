package com.auto.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.base.common.AppException;
import com.app.components.persist.IPersistService;
import com.auto.entity.DriverEntity;
import com.auto.entity.UserEntity;
import com.auto.service.IMessageService;
import com.auto.service.IMessageServiceFacade;

@Service("MessageServiceFacadeImpl")
public class MessageServiceFacadeImpl implements IMessageServiceFacade {
	private static Logger logger = LoggerFactory.getLogger(MessageServiceFacadeImpl.class);
	
	@Resource(name="PersistService")
	private IPersistService<DriverEntity> driverpersistService;
	
	@Resource(name="PersistService")
	private IPersistService<UserEntity> userPersistService;
	
	@Resource(name="MessageServiceImpl")
	private IMessageService messageService;

	@Override
	public UserEntity sendCode(String mobile, String code) throws Exception {
		UserEntity user = new UserEntity();
		
		DriverEntity driver = new DriverEntity();
		driver.setMobileNo(mobile);
		driver = driverpersistService.load(driver);
		if(driver == null){
			logger.warn("No driver found for mobile = " + mobile);
		}else{
			user.setOwnerId(driver.getDriverId());
			user = userPersistService.load(user);
			
			if(user == null){
				logger.warn("No User found for driver = " + driver.getDriverId());
			}
		}
		
		String status = messageService.send(mobile, code);
		if(! "000000".equals(status)){
			throw new AppException("验证码发送失败。");
		}
		
		return user;
	}
	
}
