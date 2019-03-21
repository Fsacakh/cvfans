package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Entity(name="StationBrand")
@Table(name="T_AUTO_STATION_BRAND")
/**
 * 服务站品牌实体类
 */
public class StationBrandEntity extends  com.app.base.entity.Entity<String> {
	private static final long serialVersionUID = 1074302032676506386L;
	
	@Id	
	private @Column(name="ID") String id; // driverId
	
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="STATION_ID") String stationId; // stationId 
	private @Column(name="BRAND") String brand; // brandId 
	private @Column(name="TYPE") Integer type; // type 

	public StationBrandEntity(){}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setStationId(String stationId){
		this.stationId = stationId;
	}
	
	public String getStationId(){
		return this.stationId;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}
	
	public String getBrand(){
		return this.brand;
	}

	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}


	@Override
	public String getId() {
		return this.id;
	}
}