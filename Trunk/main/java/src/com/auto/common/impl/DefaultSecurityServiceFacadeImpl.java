package com.auto.common.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.components.security.ISecurityServiceFacade;

@Service("DefaultSecurityServiceFacade")
public class DefaultSecurityServiceFacadeImpl implements ISecurityServiceFacade {
	@Autowired
	private ISessionManager sessionManager;
	
	public DefaultSecurityServiceFacadeImpl() {
	}
	
	@Override
	public boolean isControlled(HttpServletRequest request) throws Exception {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		return uri.startsWith(contextPath + "/backend/"); 
	}

	@Override
	public boolean isAuthorized(HttpServletRequest request) throws Exception {
		return true;
	}

	@Override
	public boolean isAuthenticated(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		if(session == null) return false;
		
		return session.getAttribute(ISessionManager.USER_KEY) != null;
	}
}
