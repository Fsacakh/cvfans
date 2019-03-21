package com.auto.hook;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.app.base.common.AppException;
import com.app.base.utils.StringUtils;
import com.app.components.persist.IPersistService;
import com.auto.common.impl.DefaultPersistHook;
import com.auto.entity.AutoInfoEntity;

@Component("AutoPersistHooker")
public class AutoPersistHooker extends DefaultPersistHook<AutoInfoEntity> {
	@Resource(name="PersistService")
	private IPersistService<AutoInfoEntity> persistService;
	
	@Override
	protected void preInsert(AutoInfoEntity entity) throws Exception {
		super.preInsert(entity);
		
		checkPlateNo(entity);
	}

	@Override
	protected void preUpdate(AutoInfoEntity entity) throws Exception {
		super.preUpdate(entity);
		
		checkPlateNo(entity);
	}
	
	
	private void checkPlateNo(AutoInfoEntity entity) throws Exception{
		String plateNo = entity.getPlateNo();
		
		if(! StringUtils.isNullOrEmpty(plateNo)){
			AutoInfoEntity queryEntity = new AutoInfoEntity();
			queryEntity.setPlateNo(plateNo);
			queryEntity = persistService.load(queryEntity);
			
			if( queryEntity != null && ! queryEntity.getAutoId().equals(entity.getAutoId())){
				throw new AppException("牌照号【" + plateNo + "】已经注册，不能重复注册。");
			};
		}
	}

}
