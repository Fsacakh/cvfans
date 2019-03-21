package com.auto.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.PersistHook;


@Table(name="T_AUTO_STATION_CUSTOMER_INFO")
@Entity(name="StationCustomerInfo")
/**
 * 服务站客户实体
 */
@PersistHook("DefaultPersistHook")
public class StationCustomerInfoEntity extends BaseEntity<String>{
	private static final long serialVersionUID = 2679126163681907044L;
	
	@Id
    private  @Column(name="CUSTOMER_ID")String  customerId;
    private  @Column(name="STATION_ID")String  stationId;
    private  @Column(name="DRIVER_ID")String  driverId;
    
    public  StationCustomerInfoEntity(){}
	
    public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	@Override
	public String getId() {
		return customerId;
	}
	
	public int getDeleted() {
		return 0;
	}

	public void setDeleted(int deleted) {
	}

	public String getCreatedUserId() {
		return null;
	}

	public void setCreatedUserId(String createdUserId) {
	}
	
	public Date getCreatedDate() {
		return null;
	}
	
	public void setCreatedDate(Date createdDate) {
	}
	
	public String getUpdatedUserId() {
		return null;
	}
	
	public void setUpdatedUserId(String updatedUserId) {
	}
	
	public Date getUpdatedDate() {
		return null;
	}

}
