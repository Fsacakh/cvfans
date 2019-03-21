package com.auto.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.base.common.AppException;
import com.app.base.utils.DateUtils;
import com.app.base.web.RequestContext;
import com.app.components.persist.IPersistService;
import com.app.components.persist.PersistServiceFacadeImpl;
import com.auto.UserManager;
import com.auto.entity.AutoGuaranteeEntity;
import com.auto.entity.AutoInfoEntity;
import com.auto.entity.StationEntity;

@Service("AutoGuaranteeServiceFacade")
public class AutoGuaranteeServiceFacade extends PersistServiceFacadeImpl<AutoGuaranteeEntity> {
	private static Logger logger = LoggerFactory.getLogger(AutoGuaranteeServiceFacade.class);
	
	@Autowired
	private IPersistService<AutoInfoEntity> autoPersistService;
	
	@Autowired
	private IPersistService<StationEntity> stationPersistService;
	
	@Override
	public void insert(AutoGuaranteeEntity entity) throws Exception {
		entity.setApplUserId(UserManager.getUserId());
		entity.setApplDate(DateUtils.getCurrentDate());
		super.insert(entity);
		
		AutoInfoEntity auto = new AutoInfoEntity(entity.getAutoId());
		auto = autoPersistService.load(auto);
		if(auto == null){
			logger.warn("No auto info found for autoId=" + entity.getAutoId());
			
			throw new AppException("担保的车辆不存在。");
		}
		
		auto.setGuarantee(1);
		
		StationEntity station = new StationEntity(entity.getStationId());
		station = this.stationPersistService.load(station);
		if(station != null){
			auto.setStationId(station.getStationId());
			auto.setStationName(station.getName());
			auto.setStationContacter(station.getContacter());
			auto.setStationContactTel(station.getContactTel());
		}else{
			logger.warn("No station info found for station=" + entity.getStationId());
			throw new AppException("担保服务站不存在。");
		}
		
		autoPersistService.update(auto);
	}

	@Override
	public void update(AutoGuaranteeEntity entity) throws Exception {
		String url = RequestContext.getRequestURI();
		if(url.contains("/Persist/Approve")){
			entity.setApprUserId(UserManager.getUserId());
			entity.setApprDate(DateUtils.getCurrentDate());
			
			AutoInfoEntity auto = new AutoInfoEntity(entity.getAutoId());
			auto = autoPersistService.load(auto);
			if(auto == null){
				logger.warn("No auto info found for autoId=" + entity.getAutoId());
				
				throw new AppException("担保的车辆不存在。");
			}
			
			if(entity.getApprResult() == 2){ //担保
				StationEntity station = new StationEntity(entity.getStationId());
				station = this.stationPersistService.load(station);
				if(station != null){
					auto.setStationId(station.getStationId());
					auto.setStationName(station.getName());
					auto.setStationContacter(station.getContacter());
					auto.setStationContactTel(station.getContactTel());
				}else{
					logger.warn("No station info found for station=" + entity.getStationId());
					throw new AppException("担保服务站不存在。");
				}
			}else{
				auto.setStationId("");
				auto.setStationName("");
				auto.setStationContacter("");
				auto.setStationContactTel("");
			}
			
			auto.setGuarantee(entity.getApprResult());
			
			autoPersistService.update(auto);
		}
		
		super.update(entity);
	}

	@Override
	public void delete(AutoGuaranteeEntity entity,List<Map<String, Object>> values) throws Exception {
		super.delete(entity, values);
		
		for(Map<String, Object> value : values){
			AutoInfoEntity auto = new AutoInfoEntity(value.get("autoId").toString());
			auto = autoPersistService.load(auto);
			if(auto == null){
				logger.warn("No auto info found for autoId=" + entity.getAutoId());
			}
			
			auto.setStationId("");
			auto.setStationName("");
			auto.setStationContacter("");
			auto.setStationContactTel("");
			auto.setGuarantee(0);
			
			autoPersistService.update(auto);
		}
	}
}
