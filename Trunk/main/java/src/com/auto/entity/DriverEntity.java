package com.auto.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.PersistHook;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Entity(name="Driver")
@Table(name="T_AUTO_DRIVER")
/**
 * 司机实体类
 */

@PersistHook("DriverPersistHook")
public class DriverEntity extends  BaseEntity<String> {
	private static final long serialVersionUID = -1318330239780519760L;

	@Id	
	private @Column(name="DRIVER_ID") String driverId; // driverId
	
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="NICK_NAME") String nickName; // nickName 
	private @Column(name="NAME") String name; // name 
	private @Column(name="PROVINCE") String province; // province 
	private @Column(name="PROVINCE_NAME") String provinceName; // provinceName 
	private @Column(name="CITY") String city; // city 
	private @Column(name="CITY_NAME") String cityName; // cityName
	private @Column(name="AREA") String area; // area 
	private @Column(name="AREA_NAME") String areaName; // areaName 
	private @Column(name="ADDRESS") String address; // address
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="MOBILE_NO") String mobileNo; // mobileNo 
	private @Column(name="BORN_DATE") String bornDate; // bornDate 
	private @Column(name="BG_LOGO_FILE") String bgLogoFile; // bgLogoFile 
	private @Column(name="LOGO_FILE") String logoFile; // logoFile
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="LICENSE_NO") String licenseNo; // licenseNo 
	private @Column(name="LICENSE_FILE") String licenseFile; // licenseFile 
	private @Column(name="LICENSE_DATE") Date licenseDate; // licenseDate 
	private @Column(name="LICENSE_AUTHENTICATED") Integer licenseAuthenticated; // licenseAuthenticated 0:认证 1：已认证 2：待认证
	private @Column(name="LICENSE_AUTHENTICATED_MEMO") String licenseAuthenticatedMemo; // licenseAuthenticated 0:认证 1：已认证 2：待认证
	private @Column(name="MEMBER_LEVEL") Integer memberLevel; // memberLevel 
	private @Column(name="MEMBER_SCORE") Integer memberScore; // memberScore 
	private @Column(name="MOBILE_BINDED") Integer mobileBinded; // mobileBinded 
	
	public DriverEntity(){}
	
	public DriverEntity(String driverId){
		this.driverId = driverId;
	}

	public void setDriverId(String driverId){
		this.driverId = driverId;
	}
	
	public String getDriverId(){
		return this.driverId;
	}

	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	
	public String getNickName(){
		return this.nickName;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}

	public void setProvince(String province){
		this.province = province;
	}
	
	public String getProvince(){
		return this.province;
	}

	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return this.city;
	}

	public void setArea(String area){
		this.area = area;
	}
	
	public String getArea(){
		return this.area;
	}

	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return this.address;
	}

	public void setMobileNo(String mobileNo){
		this.mobileNo = mobileNo;
	}
	
	public String getMobileNo(){
		return this.mobileNo;
	}

	public void setBornDate(String bornDate){
		this.bornDate = bornDate;
	}
	
	public String getBornDate(){
		return this.bornDate;
	}

	public void setBgLogoFile(String bgLogoFile){
		this.bgLogoFile = bgLogoFile;
	}
	
	public String getBgLogoFile(){
		return this.bgLogoFile;
	}

	public void setLogoFile(String logoFile){
		this.logoFile = logoFile;
	}
	
	public String getLogoFile(){
		return this.logoFile;
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

	public void setLicenseDate(Date licenseDate){
		this.licenseDate = licenseDate;
	}
	
	public Date getLicenseDate(){
		return this.licenseDate;
	}

	public void setLicenseAuthenticated(Integer licenseAuthenticated){
		this.licenseAuthenticated = licenseAuthenticated;
	}
	
	public Integer getLicenseAuthenticated(){
		return this.licenseAuthenticated;
	}

	public String getLicenseAuthenticatedMemo() {
		return licenseAuthenticatedMemo;
	}

	public void setLicenseAuthenticatedMemo(String licenseAuthenticatedMemo) {
		this.licenseAuthenticatedMemo = licenseAuthenticatedMemo;
	}

	public void setMemberLevel(Integer memberLevel){
		this.memberLevel = memberLevel;
	}
	
	public Integer getMemberLevel(){
		return this.memberLevel;
	}

	public void setMemberScore(Integer memberScore){
		this.memberScore = memberScore;
	}
	
	public Integer getMemberScore(){
		return this.memberScore == null ? 0 : memberScore;
	}

	public void setMobileBinded(Integer mobileBinded){
		this.mobileBinded = mobileBinded;
	}
	
	public Integer getMobileBinded(){
		return this.mobileBinded;
	}
	
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String getId() {
		return driverId;
	}
}