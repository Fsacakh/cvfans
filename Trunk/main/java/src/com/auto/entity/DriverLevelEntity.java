package com.auto.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="DriverLevel")
@Table(name="T_AUTO_DRIVER_LEVEL")
/**
 * 司机等级实体类
 */
public class DriverLevelEntity extends  BaseEntity<String> {
	
	private static final long serialVersionUID = 4360358926336176198L;
	@Id	
	private @Column(name="LEVEL_ID") String levelId; // levelId 
	private @Column(name="DRIVER_ID") String driverId; // driverId 
	private @Column(name="LEVEL") Integer level; // level 
	private @Column(name="UP_DATE") Date upDate; // upDate 
	
	public DriverLevelEntity(){}
	
	public DriverLevelEntity(String id)
	{
		this.levelId=id;
	}
	
	public void setLevelId(String levelId){
		this.levelId = levelId;
	}
	
	public String getLevelId(){
		return this.levelId;
	}

	public void setDriverId(String driverId){
		this.driverId = driverId;
	}
	
	public String getDriverId(){
		return this.driverId;
	}

	public void setLevel(Integer level){
		this.level = level;
	}
	
	public Integer getLevel(){
		return this.level;
	}

	public void setUpDate(Date upDate){
		this.upDate = upDate;
	}
	
	public Date getUpDate(){
		return this.upDate;
	}


	@Override
	public String getId() {
		return levelId;
	}
}