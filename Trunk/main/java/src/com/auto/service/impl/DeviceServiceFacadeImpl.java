package com.auto.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.base.utils.StringUtils;
import com.app.components.persist.IPersistService;
import com.app.components.persist.PersistServiceFacadeImpl;
import com.auto.entity.DeviceInfoEntity;
import com.auto.entity.DeviceVersionEntity;
import com.auto.entity.UserEntity;
import com.auto.service.IDeviceServiceFacade;

@Service("DeviceServiceFacadeImpl")
public class DeviceServiceFacadeImpl extends PersistServiceFacadeImpl<DeviceVersionEntity> implements IDeviceServiceFacade<DeviceVersionEntity>{
	@Resource(name="PersistService")
	private IPersistService<DeviceInfoEntity> devicePersistService;
	
	@Resource(name="PersistService")
	private IPersistService<UserEntity> userPersistService;
	
	@Override
	public DeviceVersionEntity getLatestVersionInfo(DeviceVersionEntity version, String userId, String uuid, String devType)
	throws Exception {
		//注册设备
		if(StringUtils.isNotNullOrEmpty(devType) && StringUtils.isNotNullOrEmpty(userId)){
			UserEntity user = new UserEntity(userId);
			user = this.userPersistService.load(user);
			
			if(user != null){
				DeviceInfoEntity device = new DeviceInfoEntity();
				device.setDevNo(uuid);
				device.setDevType(devType);
				device = devicePersistService.load(device);
				if(device == null){
					device = new DeviceInfoEntity();
					device.setDevNo(uuid);
					device.setDevType(devType);
					device.setUserId(user.getUserId());
					devicePersistService.insert(device);
				}else{
					device.setUserId(user.getUserId());
					devicePersistService.update(device);
				}
			}
		}
				
		return super.load(version);
	}
}
