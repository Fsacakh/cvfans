package com.auto.common.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.app.base.web.RequestContext;

@Component("SessionManager")
public class DefaultSessionManager implements ISessionManager {
	public DefaultSessionManager() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAttribute(String name, Object defaultValue) {
		Object value = null;
		HttpServletRequest request = RequestContext.getHttpServletRequest();
		if(request != null){
			HttpSession session = request.getSession();
			value = session.getAttribute(name);
		}
		
		return (T) (value == null ? defaultValue : value);
	}

	@Override
	public <T> T getAttribute(String name) {
		return getAttribute(name, null);
	}
}
