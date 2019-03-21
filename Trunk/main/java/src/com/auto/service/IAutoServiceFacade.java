package com.auto.service;

import com.auto.entity.ServiceDtailsEntity;

public interface IAutoServiceFacade 
{
	/**
	 * 维修单查询
	 * @param serviceId
	 * @param ownerRole
	 * @param ownerId
	 * @return
	 * @throws Exception
	 */
	public ServiceDtailsEntity serviceDetails(String serviceId,String ownerId) throws Exception;
	
	/**
	 * 车辆注册
	 * @param plateNo
	 * @param ownerId
	 * @param address
	 * @param brand
	 * @param brandName
	 * @param model
	 * @param modelName
	 * @param engineNo
	 * @param ownerType
	 * @param frameNo
	 * @param licenseFileUri
	 */
	public void registerAuto(String plateNo,String  ownerId,String address,String brand,String brandName,String  model,String modelName,String engineNo,String  frameNo,String ownerRole,String licenseFileUri ) throws Exception;
}
