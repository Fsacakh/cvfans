package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Enterprise")
@Table(name="T_AUTO_ENTERPRISE")
/**
 * 车（企业）管理实体类
 */
public class EnterpriseEntity extends  BaseEntity<String> {

	private static final long serialVersionUID = -5760836729391263998L;
	@Id	
	private @Column(name="ENTERPRISE_ID") String enterpriseId; // enterpriseId 
	private @Column(name="NAME") String name; // name 
	private @Column(name="PROVINCE") String province; // province
	private @Column(name="PROVINCE_NAME") String provinceName; // province
	private @Column(name="CITY") String city; // city
	private @Column(name="CITY_NAME") String cityName; // city
	private @Column(name="AREA") String area; // area
	private @Column(name="AREA_NAME") String areaName; // area
	private @Column(name="ADDRESS") String address; // address 
	private @Column(name="CONTACTER") String contacter; // contacter 
	private @Column(name="CONTACT_TEL") String contactTel; // contactTel 
	private @Column(name="FIN_CONTACTER") String finContacter; // finContacter 
	private @Column(name="FIN_CONTACT_MOBILE") String finContactMobile; // finContactMobile
	private @Column(name="LOGO_FILE") String logoFile; // city
	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	private @Column(name="LICENSE_FILE") String licenseFile; // licenseFile
	private @Column(name="USER_STATUS") Integer userStatus = 0;
	
	public EnterpriseEntity(){}
	
	public EnterpriseEntity(String enterpriseId){
		this.enterpriseId = enterpriseId;
	}
	

	public void setEnterpriseId(String enterpriseId){
		this.enterpriseId = enterpriseId;
	}
	
	public String getEnterpriseId(){
		return this.enterpriseId;
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

	public void setContacter(String contacter){
		this.contacter = contacter;
	}
	
	public String getContacter(){
		return this.contacter;
	}

	public void setContactTel(String contactTel){
		this.contactTel = contactTel;
	}
	
	public String getContactTel(){
		return this.contactTel;
	}

	public void setFinContacter(String finContacter){
		this.finContacter = finContacter;
	}
	
	public String getFinContacter(){
		return this.finContacter;
	}

	public void setFinContactMobile(String finContactMobile){
		this.finContactMobile = finContactMobile;
	}
	
	public String getFinContactMobile(){
		return this.finContactMobile;
	}

	public void setLicenseFile(String licenseFile){
		this.licenseFile = licenseFile;
	}
	
	public String getLicenseFile(){
		return this.licenseFile;
	}

	/**
	 * 【卡车之友】欢迎加入卡车之友联盟！您的短信验证码是{1}，请在{2}分钟内输入使用。超时请重新申请。
	 */

	@Override
	public String getId() {
		return enterpriseId;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus==null?0:userStatus;
	}
}