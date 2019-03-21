package com.auto.common.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.app.components.view.IViewServiceFacade;

@Service("ViewServiceFacade")
public class DefaultViewServiceFacadeImpl implements IViewServiceFacade {
	public DefaultViewServiceFacadeImpl() {
	}

	@Override
	public void initialize(String view, ModelMap map, HttpServletRequest request)
			throws Exception {
		map.put("user", request.getSession().getAttribute(ISessionManager.USER_KEY));
	}
}
