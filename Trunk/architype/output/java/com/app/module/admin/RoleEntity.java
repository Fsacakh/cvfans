package com.auto.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.app.base.entity.annotation.Validator;
import com.app.base.entity.annotation.Validators;
import com.app.base.sao.rdbms.Constraint;
import com.app.modules.common.BaseEntity;
import com.app.modules.common.Variable;

@Scope("prototype")
@Component("Auto")

@Entity(name="Auto")
@Table(name="T_AUTO_INFO")
public class AutoEntity extends  BaseEntity<String> {
	private @Column(name="AUTO_ID") String autoId; // autoId 
	private @Column(name="LICENSE_NO") String licenseNo; // licenseNo 
	private @Column(name="LICENSE_FILE") String licenseFile; // licenseFile 
	private @Column(name="PLATE") String plate; // plate 
	private @Column(name="PLATE_NO") String plateNo; // plateNo 
	private @Column(name="BRAND") String brand; // brand 
	private @Column(name="MODEL") String model; // model 
	private @Column(name="ENGINE_NO") String engineNo; // engineNo 
	private @Column(name="FRAME_NO") String frameNo; // frameNo 
	private @Column(name="OWNER_ID") String ownerId; // ownerId 
	private @Column(name="OWNER_TYPE") Integer ownerType; // ownerType 
	private @Column(name="CREATED_USER_ID") String createdUserId; // createdUserId 
	private @Column(name="CREATED_DATE") Date createdDate; // createdDate 
	private @Column(name="UPDATED_USER_ID") String updatedUserId; // updatedUserId 
	private @Column(name="UPDATED_DATE") Date updatedDate; // updatedDate 
	private @Column(name="IS_DELETED") Integer isDeleted; // isDeleted 
	
	public AutoEntity(){}
	

	public void setAutoId(String autoId){
		this.autoId = autoId;
	}
	
	public String getAutoId(){
		return this.autoId;
	}

	public void setLicenseNo(String licenseNo){
		this.licenseNo = licenseNo;
	}
	
	public String getLicenseNo(){
		return this.licenseNo;
	}

	public void setLicenseFile(String licenseFile){
		this.licenseFile = licenseFile;
	}
	
	public String getLicenseFile(){
		return this.licenseFile;
	}

	public void setPlate(String plate){
		this.plate = plate;
	}
	
	public String getPlate(){
		return this.plate;
	}

	public void setPlateNo(String plateNo){
		this.plateNo = plateNo;
	}
	
	public String getPlateNo(){
		return this.plateNo;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}
	
	public String getBrand(){
		return this.brand;
	}

	public void setModel(String model){
		this.model = model;
	}
	
	public String getModel(){
		return this.model;
	}

	public void setEngineNo(String engineNo){
		this.engineNo = engineNo;
	}
	
	public String getEngineNo(){
		return this.engineNo;
	}

	public void setFrameNo(String frameNo){
		this.frameNo = frameNo;
	}
	
	public String getFrameNo(){
		return this.frameNo;
	}

	public void setOwnerId(String ownerId){
		this.ownerId = ownerId;
	}
	
	public String getOwnerId(){
		return this.ownerId;
	}

	public void setOwnerType(Integer ownerType){
		this.ownerType = ownerType;
	}
	
	public Integer getOwnerType(){
		return this.ownerType;
	}

	public void setCreatedUserId(String createdUserId){
		this.createdUserId = createdUserId;
	}
	
	public String getCreatedUserId(){
		return this.createdUserId;
	}

	public void setCreatedDate(Date createdDate){
		this.createdDate = createdDate;
	}
	
	public Date getCreatedDate(){
		return this.createdDate;
	}

	public void setUpdatedUserId(String updatedUserId){
		this.updatedUserId = updatedUserId;
	}
	
	public String getUpdatedUserId(){
		return this.updatedUserId;
	}

	public void setUpdatedDate(Date updatedDate){
		this.updatedDate = updatedDate;
	}
	
	public Date getUpdatedDate(){
		return this.updatedDate;
	}

	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}
	
	public Integer getIsDeleted(){
		return this.isDeleted;
	}
}