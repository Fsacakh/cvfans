package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Entity(name="Discount")
@Table(name="T_AUTO_STATION_DISCOUNT")
/**
 * 服务站折扣信息实体类
 */
public class StationDiscountEntity extends  BaseEntity<String> {
	
	private static final long serialVersionUID = -6742247004755232269L;
	@Id	
	private @Column(name="DISCOUNT_ID") String discountId; // discountId
	
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="STATION_ID") String stationId; // stationId
	private @Column(name="ITEM") String item;
	private @Column(name="TIME_DISCOUNT") String timeDiscount; // timeDiscount 
	private @Column(name="MATERIAL_DISCOUNT") String materialDiscount; // materialDiscount
	private @Column(name="MEMO") String memo;
	
	public StationDiscountEntity(){}
	
	public StationDiscountEntity(String id)
	{
		this.discountId=id;
	}

	public void setDiscountId(String discountId){
		this.discountId = discountId;
	}
	
	public String getDiscountId(){
		return this.discountId;
	}

	public void setStationId(String stationId){
		this.stationId = stationId;
	}
	
	public String getStationId(){
		return this.stationId;
	}

	public String getTimeDiscount() {
		return timeDiscount;
	}

	public void setTimeDiscount(String timeDiscount) {
		this.timeDiscount = timeDiscount;
	}

	public String getMaterialDiscount() {
		return materialDiscount;
	}

	public void setMaterialDiscount(String materialDiscount) {
		this.materialDiscount = materialDiscount;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String getId() {
		return discountId;
	}
}