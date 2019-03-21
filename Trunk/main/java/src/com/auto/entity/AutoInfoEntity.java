package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.PersistFacade;
import com.app.base.entity.annotation.PersistHook;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;
import com.auto.common.Variable;

@Entity(name="AutoInfo")
@Table(name="T_AUTO_INFO")
/**
 * 车辆注册实体类
 */
@PersistHook("AutoPersistHooker")
@PersistFacade("AutoServiceFacade")
public class AutoInfoEntity extends  BaseEntity<String> {
	
	private static final long serialVersionUID = -1315551978318425829L;
	@Id	
	private @Column(name="AUTO_ID") String autoId; // autoId 
	private @Column(name="LICENSE_NO") String licenseNo; // licenseNo 
	private @Column(name="LICENSE_FILE") String licenseFileUri; // licenseFile 
	private @Column(name="PLATE") String plate; // plate 
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="PLATE_NO") String plateNo; // plateNo 
	
	private @Column(name="TYPE") String type; // brand
	private @Column(name="TYPE_NAME") String typeName; // brand
	private @Column(name="BRAND") String brand; // brand 
	private @Column(name="BRAND_NAME") String brandName; // brand 
	private @Column(name="MODEL") String model; // model
	private @Column(name="MODEL_NAME") String modelName; // model
	private @Column(name="SCORE") int	score = 0;
	private @Column(name="ENGINE_NO") String engineNo; // engineNo
	
	private @Column(name="FRAME_NO") String frameNo; // frameNo
	
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="OWNER_ID") String ownerId; // ownerId 
	private @Column(name="OWNER_TYPE") Integer ownerType; // 1 车管  2 个人
	private @Column(name="OWNER_NAME") String ownerName; // 1 车管  2 个人
	private @Column(name="OWNER_CONTACTER") String ownerContacter; // 1 车管  2 个人
	private @Column(name="OWNER_CONTACT_TEL") String ownerContactTel; // 1 车管  2 个人
	
	private @Column(name="GUARANTEE") Integer guarantee = Variable.Guarantee.Noapplication.code;
	private @Column(name="STATION_ID") String stationId;
	private @Column(name="STATION_NAME") String stationName;
	private @Column(name="STATION_CONTACTER") String stationContacter;
	private @Column(name="STATION_CONTACT_TEL") String stationContactTel;
	private @Column(name="AUTO_LEVEL") Integer autoLevel; // autoLevel
	private @Column(name="CERTIFICATION") Integer certification; // certification

	public AutoInfoEntity(){}
	
	public Integer getCertification() {
		return certification;
	}

	public void setCertification(Integer certification) {
		this.certification = certification;
	}

	public Integer getAutoLevel() {
		return autoLevel;
	}

	public void setAutoLevel(Integer autoLevel) {
		this.autoLevel = autoLevel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AutoInfoEntity(String id)
	{
		this.autoId=id;
	}

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

	public void setLicenseFileUri(String licenseFileUri){
		this.licenseFileUri = licenseFileUri;
	}
	
	public String getLicenseFileUri(){
		return this.licenseFileUri;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getTypeName() {
		return typeName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerContacter() {
		return ownerContacter;
	}

	public void setOwnerContacter(String ownerContacter) {
		this.ownerContacter = ownerContacter;
	}

	public String getOwnerContactTel() {
		return ownerContactTel;
	}

	public void setOwnerContactTel(String ownerContactTel) {
		this.ownerContactTel = ownerContactTel;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationContacter() {
		return stationContacter;
	}

	public void setStationContacter(String stationContacter) {
		this.stationContacter = stationContacter;
	}

	public String getStationContactTel() {
		return stationContactTel;
	}

	public void setStationContactTel(String stationContactTel) {
		this.stationContactTel = stationContactTel;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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


	public Integer getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(Integer guarantee) {
		this.guarantee = guarantee;
	}

	@Override
	public String getId() {
		return autoId;
	}
}