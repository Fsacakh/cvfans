package com.auto;

import com.app.base.utils.BeanUtils;
import com.app.base.web.RequestContext;
import com.app.components.persist.IPersistService;
import com.auto.common.impl.ISessionManager;
import com.auto.entity.UserEntity;

public class UserManager {
	public static String getUserId() throws Exception{
		UserEntity user = getUserEntity();
		if(user != null){
			return user.getUserId();
		}else{
			return "";
		}
	}
	
	public static int getUserRole() throws Exception{
		UserEntity user = getUserEntity();
		if(user != null){
			return user.getOwnerRole();
		}else{
			return -1;
		}
	}
	
	private static UserEntity getUserEntity() throws Exception{
		String userId = null;
		if(RequestContext.getHttpServletRequest()!=null){
		 userId = RequestContext.getHttpServletRequest().getHeader("userId"); //满足移动终端访问
		}
		if(userId == null){
			return getCachedUserEntity();
		}else{
			return getUserEntity(userId);
		}
	}
	
	private static UserEntity getCachedUserEntity() throws Exception{
		ISessionManager sessionManager = BeanUtils.getBean(ISessionManager.class);
		
		return sessionManager.getAttribute(ISessionManager.USER_KEY);
		
	}
	
	private static UserEntity getUserEntity(String userId) throws Exception{
		IPersistService<UserEntity> userPersistService = BeanUtils.getBean("PersistService");
		
		UserEntity user = new UserEntity(userId);
		return userPersistService.load(user); 
	}
}
