package com.auto.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.base.common.AppException;
import com.app.components.persist.IPersistService;
import com.app.components.persist.PersistServiceFacadeImpl;
import com.auto.entity.StationCommentEntity;
import com.auto.entity.StationEntity;

@Service("ComentServiceFacade")
public class ComentServiceFacade extends PersistServiceFacadeImpl<StationCommentEntity>{
private static Logger logger = LoggerFactory.getLogger(ComentServiceFacade.class);
	
	@Resource(name="PersistService")
	private IPersistService<StationCommentEntity> commentPersistService;
	
	@Resource(name="PersistService")
	private IPersistService<StationEntity> stationPersistService;
	
	List<StationCommentEntity> list=null;
	
	@Override
	public void insert(StationCommentEntity entity) throws Exception {
		
		StationEntity station=new StationEntity(entity.getStationId());
		station=stationPersistService.load(station);
		if(station==null)
		{
			logger.warn("评论服务站已不存在-------"+entity.getStationId());
			throw new AppException("评论服务站已不存在!"); 
		}
		super.insert(entity);
		if(station.getCommentCount()==null)
		{
			station.setCommentCount(0);
		}
		
		if(station.getGeneralScore()==null)
		{
			station.setGeneralScore(0.0);
		}
		
		if(station.getQualityScore()==null)
		{
			station.setQualityScore(0.0);
		}
		
		if(station.getTimeScore()==null)
		{
			station.setTimeScore(0.0);
		}
		
		if(station.getPriceScore()==null)
		{
			station.setPriceScore(0.0);
		}
		
		if(station.getBehaviorScore()==null)
		{
			station.setBehaviorScore(0.0);
		}
		
		station.setGeneralScore((station.getCommentCount()*station.getGeneralScore()+entity.getGeneralScore())/(station.getCommentCount()+1));
		station.setQualityScore((station.getCommentCount()*station.getQualityScore()+entity.getQualityScore())/(station.getCommentCount()+1));
		station.setTimeScore((station.getCommentCount()*station.getTimeScore()+entity.getTimeScore())/(station.getCommentCount()+1));
		station.setPriceScore((station.getCommentCount()*station.getPriceScore()+entity.getPriceScore())/(station.getCommentCount()+1));
		station.setBehaviorScore((station.getCommentCount()*station.getBehaviorScore()+entity.getBehaviorScore())/(station.getCommentCount()+1));
		station.setCommentCount(station.getCommentCount()+1);
		stationPersistService.update(station);
	}
}
