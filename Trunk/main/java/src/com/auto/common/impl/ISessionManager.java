package com.auto.common.impl;

/**
 * 该接口定义了从Session中获取属性值
 * @author Tony.Zhang
 *
 */
public interface ISessionManager {
	public final static String USER_KEY = "User";
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public <T> T getAttribute(String name, Object defaultValue);
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public <T> T getAttribute(String name);
}
