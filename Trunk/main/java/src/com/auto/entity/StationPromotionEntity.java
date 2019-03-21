package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Promotion")
@Table(name="T_AUTO_STATION_PROMOTION")
/**
 * 服务站促销信息实体类
 */
public class StationPromotionEntity extends  BaseEntity<String> {
	private static final long serialVersionUID = 3425166582122611623L;

	@Id	
	private @Column(name="PROMOTION_ID") String promotionId; // promotionId 
	private @Column(name="STATION_ID") String stationId; // stationId 
	private @Column(name="TITLE") String title; // title 
	private @Column(name="CONTENT") String content; // content 
	private @Column(name="TIMELINESS") String timeliness; // timeliness 
	private @Column(name="STATUS") Integer status; // status 
	private @Column(name="IS_TOP") Integer isTop = 0; // isTop 
	
	public StationPromotionEntity(){}

	public StationPromotionEntity(String id)
	{
		this.promotionId=id;
	}

	public void setPromotionId(String promotionId){
		this.promotionId = promotionId;
	}
	
	public String getPromotionId(){
		return this.promotionId;
	}

	public void setStationId(String stationId){
		this.stationId = stationId;
	}
	
	public String getStationId(){
		return this.stationId;
	}

	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return this.title;
	}

	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return this.content;
	}

	public void setTimeliness(String timeliness){
		this.timeliness = timeliness;
	}
	
	public String getTimeliness(){
		return this.timeliness;
	}

	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}

	public void setIsTop(Integer isTop){
		this.isTop = isTop;
	}
	
	public Integer getIsTop(){
		return this.isTop;
	}


	@Override
	public String getId() {
		return promotionId;
	}
}