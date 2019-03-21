package com.auto.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.base.common.AppException;
import com.app.components.persist.IPersistService;
import com.app.components.persist.PersistServiceFacadeImpl;
import com.auto.UserManager;
import com.auto.entity.AutoInfoEntity;
import com.auto.entity.DriverEntity;
import com.auto.entity.EnterpriseEntity;

@Service("AutoServiceFacade")
public class AutoServiceFacade extends PersistServiceFacadeImpl<AutoInfoEntity>{
	private static Logger logger = LoggerFactory.getLogger(AutoServiceFacade.class);
	
	@Resource(name="PersistService")
	private IPersistService<AutoInfoEntity> autoInfoPersistService;
	
	@Resource(name="PersistService")
	private IPersistService<EnterpriseEntity> enterprisePersistService;
	
	@Resource(name="PersistService")
	private IPersistService<DriverEntity> driverPersistService;
	
	@Override
	public void insert(AutoInfoEntity entity) throws Exception {
		AutoInfoEntity autoInfo=new AutoInfoEntity();
		autoInfo.setPlateNo(entity.getPlateNo());
		autoInfo=autoInfoPersistService.load(autoInfo);
		if(autoInfo!=null)
		{
			logger.warn("车牌号已被注册，不能再注册。--------"+entity.getPlateNo());
			throw new AppException("车牌号已被注册过，请更换车牌号进行注册。");
		}
		
		int ownerType = UserManager.getUserRole();
		if(ownerType == 3){ //车管车辆
			entity.setOwnerType(1);
			EnterpriseEntity enterprise = new EnterpriseEntity(entity.getOwnerId());
			enterprise = this.enterprisePersistService.load(enterprise);
			if(enterprise != null){
				entity.setOwnerId(enterprise.getEnterpriseId());
				entity.setOwnerName(enterprise.getName());
				entity.setOwnerContacter(enterprise.getContacter());
				entity.setOwnerContactTel(enterprise.getContactTel());
			}
		}else{ //个人车辆
			entity.setOwnerType(2);
			DriverEntity driver = new DriverEntity(entity.getOwnerId());
			driver = this.driverPersistService.load(driver);
			if(driver != null){
				entity.setOwnerId(driver.getDriverId());
				entity.setOwnerName(driver.getName());
				entity.setOwnerContacter(driver.getName());
				entity.setOwnerContactTel(driver.getMobileNo());
			}
		}
		
		super.insert(entity);
	}

	@Override
	public void update(AutoInfoEntity entity) throws Exception {
		AutoInfoEntity autoInfo=new AutoInfoEntity();
		autoInfo.setPlateNo(entity.getPlateNo());
		autoInfo=autoInfoPersistService.load(autoInfo);
		if(autoInfo!=null && ! autoInfo.getAutoId().equals(entity.getAutoId())){
			logger.warn("车牌号已被注册，不能再注册。--------"+entity.getPlateNo());
			throw new AppException("车牌号已被注册过，请更换车牌号进行注册。");
		}
		
		int ownerType = UserManager.getUserRole();
		if(ownerType == 3){ //车管车辆
			entity.setOwnerType(1);
			EnterpriseEntity enterprise = new EnterpriseEntity(entity.getOwnerId());
			enterprise = this.enterprisePersistService.load(enterprise);
			if(enterprise != null){
				entity.setOwnerId(enterprise.getEnterpriseId());
				entity.setOwnerName(enterprise.getName());
				entity.setOwnerContacter(enterprise.getContacter());
				entity.setOwnerContactTel(enterprise.getContactTel());
			}
		}else{ //个人车辆
			entity.setOwnerType(2);
			DriverEntity driver = new DriverEntity(entity.getOwnerId());
			driver = this.driverPersistService.load(driver);
			if(driver != null){
				entity.setOwnerId(driver.getDriverId());
				entity.setOwnerName(driver.getName());
				entity.setOwnerContacter(driver.getName());
				entity.setOwnerContactTel(driver.getMobileNo());
			}
		}
		
		super.update(entity);
	}
}
