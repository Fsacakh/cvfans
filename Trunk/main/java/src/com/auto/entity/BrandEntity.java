package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Brand")
@Table(name="T_AUTO_BRAND")
/**
 * 品牌实体类
 */
public class BrandEntity extends  BaseEntity<String> {
	
	private static final long serialVersionUID = -6738561670126165202L;
	@Id	
	private @Column(name="BRAND_ID") String brandId; // brandId 
	private @Column(name="BRAND_CODE") String brandCode; // brandCode 
	private @Column(name="BRAND_NAME") String brandName; // brandName 
	
	public BrandEntity(){}
	
	public BrandEntity(String id)
	{
		this.brandId=id;
	}

	public void setBrandId(String brandId){
		this.brandId = brandId;
	}
	
	public String getBrandId(){
		return this.brandId;
	}

	public void setBrandCode(String brandCode){
		this.brandCode = brandCode;
	}
	
	public String getBrandCode(){
		return this.brandCode;
	}

	public void setBrandName(String brandName){
		this.brandName = brandName;
	}
	
	public String getBrandName(){
		return this.brandName;
	}


	@Override
	public String getId() {
		return brandId;
	}
}