package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Station")
@Table(name="T_AUTO_STATION")
/**
 * 服务站实体类
 */

public class StationEntity extends  BaseEntity<String> {
	
	private static final long serialVersionUID = 8542752570569058554L;
	@Id	
	private @Column(name="STATION_ID") String stationId; // stationId 
	private @Column(name="PARENT_ID") String parentId; // parentId 
	private @Column(name="NAME") String name; // name 
	private @Column(name="PROVINCE") String province; // province
	private @Column(name="PROVINCE_NAME") String provinceName; // province
	private @Column(name="CITY") String city; // city
	private @Column(name="CITY_NAME") String cityName; // city 
	private @Column(name="AREA") String area; // area
	private @Column(name="AREA_NAME") String areaName; // area
	private @Column(name="ADDRESS") String address; // address 
	private @Column(name="QUALIFICATION") String qualification; // qualification 
	private @Column(name="LOGO_FILE") String logoFile; // logoFile 
	private @Column(name="LICENSE_FILE") String licenseFileUri; // licenseFile 
	private @Column(name="IS_MEMBER") Integer isMember; // isMember 
	private @Column(name="BRAND") String brand; // brand
	private @Column(name="SUB_BRAND") String subBrand; // brand
	private @Column(name="CONTACTER") String contacter; // contacter 
	private @Column(name="CONTACT_TEL") String contactTel; // contactTel 
	private @Column(name="RESERVATION_USER") String reservationUser; // reservation_user
	private @Column(name="RESERVATION_PHONE") String reservationPhone;//reservation_phone
	
	private @Column(name="AUTO_SALE_USER") String autoSaleUser; // reservation_user
	private @Column(name="AUTO_SALE_PHONE") String autoSalePhone;//reservation_phone
	
	private @Column(name="PART_SALE_USER") String partSaleUser; // reservation_user
	private @Column(name="PART_SALE_PHONE") String partSalePhone;//reservation_phone
	
	private @Column(name="FAX") String fax; // fax 
	private @Column(name="SERVICE_MEMO") String serviceMemo; // serviceMemo 
	private @Column(name="STATION_SERVICE") String stationservice;
	private @Column(name="BRIEF_INTRO") String briefIntro; // briefIntro 
	private @Column(name="ACCOUNT_BANK") String accountBank; // accountBank 
	private @Column(name="ACCOUNT_NO") String accountNo; // accountNo 
	private @Column(name="PRAISE_RATE") Integer praiseRate; // praiseRate 
	private @Column(name="COMMENT_COUNT") Integer commentCount; // commentCount 
	private @Column(name="GENERAL_SCORE") Double generalScore; // generalScore 
	private @Column(name="PRICE_SCORE") Double priceScore; // priceScore 
	private @Column(name="QUALITY_SCORE") Double qualityScore; // qualityScore 
	private @Column(name="BEHAVIOR_SCORE") Double behaviorScore; // behaviorScore 
	private @Column(name="TIME_SCORE") Double timeScore; // timeScore 
	private @Column(name="POS_LONG") Double posLong; // posLong 经度
	private @Column(name="POS_LAT") Double posLat; // posLat   //纬度
	private @Column(name="USER_STATUS") Integer userStatus = 0;
	private @Column(name="SERVICE_NUM") Integer serviceNum = 0;
	
	public StationEntity(){}
	
	public StationEntity(String stationId){
		this.stationId = stationId;
	}
	
	public void setStationId(String stationId){
		this.stationId = stationId;
		
	}
	
	public String getStationId(){
		return this.stationId;
	}

	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	
	public String getParentId(){
		return this.parentId;
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

	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return this.address;
	}

	public void setQualification(String qualification){
		this.qualification = qualification;
	}
	
	public String getQualification(){
		return this.qualification;
	}

	public void setLogoFile(String logoFile){
		this.logoFile = logoFile;
	}
	
	public String getLogoFile(){
		return this.logoFile;
	}

	public void setLicenseFileUri(String licenseFileUri){
		this.licenseFileUri = licenseFileUri;
	}
	
	public Double getBehaviorScore() {
		return behaviorScore;
	}

	public void setBehaviorScore(Double behaviorScore) {
		this.behaviorScore = behaviorScore;
	}

	public Integer getServiceNum() {
		return serviceNum;
	}

	public void setServiceNum(Integer serviceNum) {
		this.serviceNum = serviceNum;
	}

	public String getLicenseFileUri(){
		return this.licenseFileUri;
	}

	public void setIsMember(Integer isMember){
		this.isMember = isMember;
	}
	
	public Integer getIsMember(){
		return this.isMember;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}
	
	public String getBrand(){
		return this.brand;
	}

	public String getSubBrand() {
		return subBrand;
	}

	public void setSubBrand(String subBrand) {
		this.subBrand = subBrand;
	}

	public void setContacter(String contacter){
		this.contacter = contacter;
	}
	
	public String getContacter(){
		return this.contacter;
	}

	public void setContactTel(String contactTel){
		this.contactTel = contactTel;
	}
	
	public Integer getUserStatus() {
		return userStatus == null ? 0 : userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus==null?0:userStatus;
	}

	public String getContactTel(){
		return this.contactTel;
	}

	public void setFax(String fax){
		this.fax = fax;
	}
	
	public String getFax(){
		return this.fax;
	}

	public void setServiceMemo(String serviceMemo){
		this.serviceMemo = serviceMemo;
	}
	
	public String getServiceMemo(){
		return this.serviceMemo;
	}

	public void setBriefIntro(String briefIntro){
		this.briefIntro = briefIntro;
	}
	
	public String getBriefIntro(){
		return this.briefIntro;
	}

	public void setAccountBank(String accountBank){
		this.accountBank = accountBank;
	}
	
	public String getAccountBank(){
		return this.accountBank;
	}

	public void setAccountNo(String accountNo){
		this.accountNo = accountNo;
	}
	
	public String getAccountNo(){
		return this.accountNo;
	}

	public void setPraiseRate(Integer praiseRate){
		this.praiseRate = praiseRate;
	}
	
	public Integer getPraiseRate(){
		return this.praiseRate;
	}

	public void setCommentCount(Integer commentCount){
		this.commentCount = commentCount;
	}
	
	public Integer getCommentCount(){
		return this.commentCount;
	}

	public void setGeneralScore(Double generalScore){
		this.generalScore = generalScore;
	}
	
	public Double getGeneralScore(){
		return this.generalScore;
	}

	public void setPriceScore(Double priceScore){
		this.priceScore = priceScore;
	}
	
	public Double getPriceScore(){
		return this.priceScore;
	}

	public void setQualityScore(Double qualityScore){
		this.qualityScore = qualityScore;
	}
	
	public Double getQualityScore(){
		return this.qualityScore;
	}

	public void setTimeScore(Double timeScore){
		this.timeScore = timeScore;
	}
	
	public Double getTimeScore(){
		return this.timeScore;
	}

	public void setPosLong(Double posLong){
		this.posLong = posLong;
	}
	
	public Double getPosLong(){
		return this.posLong;
	}

	public void setPosLat(Double posLat){
		this.posLat = posLat;
	}
	
	public Double getPosLat(){
		return this.posLat;
	}


	@Override
	public String getId() {
		return stationId;
	}

	public String getReservationUser() {
		return reservationUser;
	}

	public void setReservationUser(String StationDetail) {
		this.reservationUser = StationDetail;
	}

	public String getReservationPhone() {
		return reservationPhone;
	}

	public void setReservationPhone(String reservationPhone) {
		this.reservationPhone = reservationPhone;
	}

	public String getAutoSaleUser() {
		return autoSaleUser;
	}

	public void setAutoSaleUser(String autoSaleUser) {
		this.autoSaleUser = autoSaleUser;
	}

	public String getAutoSalePhone() {
		return autoSalePhone;
	}

	public void setAutoSalePhone(String autoSalePhone) {
		this.autoSalePhone = autoSalePhone;
	}

	public String getPartSaleUser() {
		return partSaleUser;
	}

	public void setPartSaleUser(String partSaleUser) {
		this.partSaleUser = partSaleUser;
	}

	public String getPartSalePhone() {
		return partSalePhone;
	}

	public void setPartSalePhone(String partSalePhone) {
		this.partSalePhone = partSalePhone;
	}

	public String getStationservice() {
		return stationservice;
	}

	public void setStationservice(String stationservice) {
		this.stationservice = stationservice;
	}
}