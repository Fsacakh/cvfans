package com.auto.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.components.persist.IPersistService;
import com.app.components.persist.PersistServiceFacadeImpl;
import com.auto.entity.DriverEntity;
import com.auto.entity.ScoreEntity;
import com.auto.entity.ServiceEntity;
import com.auto.entity.StationEntity;

/**
 * 车辆入站服务门面
 * @author Tony.Zhang
 *
 */
@Service("AutoInboundServiceFacadeImpl")
public class AutoInboundServiceFacadeImpl extends PersistServiceFacadeImpl<ServiceEntity> {
	@Resource(name="PersistService")
	IPersistService<StationEntity> stationPersistService;
	
	@Resource(name="PersistService")
	IPersistService<DriverEntity> driverPersistService;
	
	@Resource(name="PersistService")
	private IPersistService<ScoreEntity> scorePersistService;

	@Override
	public void insert(ServiceEntity entity) throws Exception {
		if(entity.getPaymentStatus() == 1){
			DriverEntity driver = new DriverEntity(entity.getDriverId());
			driver = driverPersistService.load(driver);
			driver.setMemberScore(driver.getMemberScore() + entity.getPaymentAmount().intValue());
			driverPersistService.update(driver);
			
			ScoreEntity score = new ScoreEntity();
			score.setComment("车辆维修");
			score.setOwnerId(driver.getDriverId());
			score.setScore(entity.getPaymentAmount().intValue());
			score.setOwnerType(1);
			scorePersistService.insert(score);
			
			entity.setScoreStatus(1);
		}
		super.insert(entity);
		
		String stationId = entity.getStationId();
		StationEntity station = new StationEntity(stationId);
		station = stationPersistService.load(station);
		if(station != null){
			station.setServiceNum(station.getServiceNum() + 1);
			stationPersistService.update(station);
		}
	}

	@Override
	public void update(ServiceEntity entity) throws Exception {
		if(entity.getPaymentStatus() == 1 && entity.getScoreStatus() != 1){
			DriverEntity driver = new DriverEntity(entity.getDriverId());
			driver = driverPersistService.load(driver);
			driver.setMemberScore(driver.getMemberScore() + entity.getPaymentAmount().intValue());
			driverPersistService.update(driver);
			
			ScoreEntity score = new ScoreEntity();
			score.setComment("车辆维修");
			score.setOwnerId(driver.getDriverId());
			score.setScore(entity.getPaymentAmount().intValue());
			score.setOwnerType(1);
			scorePersistService.insert(score);
		}
		
		super.update(entity);
	}
	
	
}
