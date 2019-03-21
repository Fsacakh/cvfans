package com.auto.service;

public interface IDriverServiceFacade
{
	/**
	 * 
	 * @param driverId
	 * @param name
	 * @param licenseNo
	 * @param licenseFile
	 * @throws Exception
	 */
	public void driverAuthentication(String driverId,String name,String licenseNo,String licenseFile) throws Exception;
	
	/**
	 * 
	 * @param driverId
	 * @param name
	 * @param licenseNo
	 * @param licenseFile
	 * @throws Exception
	 */
	public void driverAuthenticationApprove(String driverId,int licenseAuthenticated,String memo) throws Exception;
	
	/**
	 * 
	 * @param driverId
	 * @param mobileNo
	 * @throws Exception
	 */
	public void bindingPhone(String driverId,String mobileNo) throws Exception;
}
