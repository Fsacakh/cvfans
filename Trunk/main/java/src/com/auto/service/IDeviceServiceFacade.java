package com.auto.service;


public interface IDeviceServiceFacade<DeviceVersionEntity> {

	/**
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public DeviceVersionEntity getLatestVersionInfo(DeviceVersionEntity version , String userId, String uuid, String deviceType) throws Exception;
}
