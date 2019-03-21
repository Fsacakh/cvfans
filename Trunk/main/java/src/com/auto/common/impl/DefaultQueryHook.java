package com.auto.common.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.base.sao.rdbms.IQueryHook;
import com.app.base.web.RequestContext;
import com.app.components.query.DataQueryRequest;
import com.app.components.query.QueryRequest;
import com.auto.entity.UserEntity;

@Component("DefaultQueryHook")
public class DefaultQueryHook implements IQueryHook<QueryRequest> {
	@Autowired
	private ISessionManager sessionManager;
	
	public DefaultQueryHook() {
	}

	@Override
	public void preQuery(QueryRequest object) throws Exception {

		if(sessionManager != null){
			object.setParameter("user",  sessionManager.getAttribute(ISessionManager.USER_KEY));
			object.setParameter("deleted", 0);
		}

		UserEntity user = null;
		String userId =   null;
		if(RequestContext.getHttpServletRequest()!=null){
		    userId = RequestContext.getHttpServletRequest().getHeader("userId");
		}
		if(userId != null){
			user = new UserEntity(userId);
		}else{
			user = (UserEntity) sessionManager.getAttribute(ISessionManager.USER_KEY);
		}
		
		if(user == null){
			user = new UserEntity();
		}
		
		object.setParameter("user",  user);
		object.setParameter("deleted", 0);

	}

	@Override
	public void postQuery(Map<String, Object> object) throws Exception {
		
	}

	@Override
	public void postQuery(DataQueryRequest request, List<Map<String, Object>> results) throws Exception {
		for(Map<String, Object> result : results){
			this.postQuery(result);
		}
	}
}
