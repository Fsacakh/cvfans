package com.auto.common.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.app.components.security.IRequestParser;

@Component
public class DefaultRequestParser implements IRequestParser {
	public DefaultRequestParser() {
	}

	@Override
	public String getCommand(HttpServletRequest request) throws Exception {
		String path = request.getContextPath();
		String uri = request.getRequestURI();
		
		return uri.substring(path.length() + 1);
	}
}
