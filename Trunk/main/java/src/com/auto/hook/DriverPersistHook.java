package com.auto.hook;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.app.base.common.AppException;
import com.app.base.utils.StringUtils;
import com.app.components.persist.IPersistService;
import com.auto.common.impl.DefaultPersistHook;
import com.auto.entity.DriverEntity;

@Component("DriverPersistHook")
public class DriverPersistHook extends DefaultPersistHook<DriverEntity> {
	@Resource(name="PersistService")
	private IPersistService<DriverEntity> persistService;

	@Override
	protected void preInsert(DriverEntity entity) throws Exception {
		checkLicenseNo(entity);
		
		super.preInsert(entity);
	}

	@Override
	protected void preUpdate(DriverEntity entity) throws Exception {
		checkLicenseNo(entity);
		
		super.preUpdate(entity);
	}
	
	private void checkLicenseNo(DriverEntity entity) throws Exception{
		String licenseNo = entity.getLicenseNo();
		
		if(! StringUtils.isNullOrEmpty(licenseNo)){
			DriverEntity queryEntity = new DriverEntity();
			queryEntity.setLicenseNo(licenseNo);
			queryEntity = persistService.load(queryEntity);
			
			if( queryEntity != null && ! queryEntity.getDriverId().equals(entity.getDriverId())){
				throw new AppException("驾照号【" + licenseNo + "】已经注册，不能重复注册。");
			};
		}
	}
}
