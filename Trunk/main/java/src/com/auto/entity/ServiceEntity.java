package com.auto.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.PersistFacade;

@Entity(name="AutoService")
@Table(name="T_AUTO_SERVICE")
/**
 * 服务信息实体表
 */
@PersistFacade("AutoInboundServiceFacadeImpl")
public class ServiceEntity extends  BaseEntity<String> {
	private static final long serialVersionUID = -8247833763854550826L;

	@Id	
	private @Column(name="SERVICE_ID") String serviceId; // serviceId 
	private @Column(name="AUTO_ID") String autoId; // autoId 
	private @Column(name="AUTO_PLATE_NO") String plateNo; // autoId
	private @Column(name="DRIVER_ID") String driverId; // driverId 
	private @Column(name="SERVICE_CODE") String serviceCode; // serviceCode 
	private @Column(name="STATION_ID") String stationId; // stationId 
	private @Column(name="ENTER_DATE") Date enterDate; // enterDate 
	private @Column(name="LEAVE_DATE") Date leaveDate; // leaveDate 
	private @Column(name="CONTACTER") String contacter; // contacter 
	private @Column(name="CONTACT_TEL") String contactTel; // contactTel 
	private @Column(name="AUTO_LEVEL") Integer autoLevel; // autoLevel 
	private @Column(name="SERVICE_DATE") Date serviceDate; // serviceDate 
	private @Column(name="SERVICE_ITEMS") String serviceItems; // serviceItems 
	private @Column(name="PAY_MODE") Integer payMode; // payMode 1）担保支付 2：车管支付 3：个人支付
	private @Column(name="GUARANTEE_STATION_ID") String guaranteeStationId;
	private @Column(name="GUARANTEE_STATUS") Integer guaranteeStatus; // patmentStatus 1：已支付 0：未支付
	private @Column(name="SERVICE_AMOUNT") Double serviceAmount; // serviceAmount 
	private @Column(name="PAYMENT_AMOUNT") Double paymentAmount; // paymentAmount 
	private @Column(name="INVOICE_FILE") String invoiceFile; // invoiceFile 
	private @Column(name="PAYMENT_STATUS") Integer paymentStatus; // patmentStatus 1：已支付 0：未支付
	private @Column(name="SCORE_STATUS") Integer scoreStatus = 0; 
	
	public ServiceEntity(){}
	
	public ServiceEntity(String id)
	{
		this.serviceId=id;
	}

	public void setServiceId(String serviceId){
		this.serviceId = serviceId;
	}
	
	public String getServiceId(){
		return this.serviceId;
	}

	public void setAutoId(String autoId){
		this.autoId = autoId;
	}
	
	public String getAutoId(){
		return this.autoId;
	}

	public void setDriverId(String driverId){
		this.driverId = driverId;
	}
	
	public String getDriverId(){
		return this.driverId;
	}

	public void setStationId(String stationId){
		this.stationId = stationId;
	}
	
	public String getStationId(){
		return this.stationId;
	}

	public void setEnterDate(Date enterDate){
		this.enterDate = enterDate;
	}
	
	public Date getEnterDate(){
		return this.enterDate;
	}

	public void setLeaveDate(Date leaveDate){
		this.leaveDate = leaveDate;
	}
	
	public Date getLeaveDate(){
		return this.leaveDate;
	}

	public void setServiceDate(Date serviceDate){
		this.serviceDate = serviceDate;
	}
	
	public Date getServiceDate(){
		return this.serviceDate;
	}

	public void setServiceItems(String serviceItems){
		this.serviceItems = serviceItems;
	}
	
	public String getServiceItems(){
		return this.serviceItems;
	}

	public void setPayMode(Integer payMode){
		this.payMode = payMode;
	}
	
	public Integer getPayMode(){
		return this.payMode;
	}

	public void setServiceAmount(Double serviceAmount){
		this.serviceAmount = serviceAmount;
	}
	
	public Double getServiceAmount(){
		return this.serviceAmount;
	}

	public void setPaymentAmount(Double paymentAmount){
		this.paymentAmount = paymentAmount;
	}
	
	public Double getPaymentAmount(){
		return this.paymentAmount;
	}

	public void setInvoiceFile(String invoiceFile){
		this.invoiceFile = invoiceFile;
	}
	
	public Integer getGuaranteeStatus() {
		return guaranteeStatus;
	}

	public void setGuaranteeStatus(Integer guaranteeStatus) {
		this.guaranteeStatus = guaranteeStatus;
	}

	public String getGuaranteeStationId() {
		return guaranteeStationId;
	}

	public void setGuaranteeStationId(String guaranteeStationId) {
		this.guaranteeStationId = guaranteeStationId;
	}

	public Integer getScoreStatus() {
		return scoreStatus;
	}

	public void setScoreStatus(Integer scoreStatus) {
		this.scoreStatus = scoreStatus;
	}

	public String getInvoiceFile(){
		return this.invoiceFile;
	}

	public void setPaymentStatus(Integer paymentStatus){
		this.paymentStatus = paymentStatus;
	}
	
	public Integer getPaymentStatus(){
		return this.paymentStatus;
	}


	@Override
	public String getId() {
		return serviceId;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setAutoLevel(Integer autoLevel) {
		this.autoLevel = autoLevel;
	}

	public Integer getAutoLevel() {
		return autoLevel;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getContacter() {
		return contacter;
	}
}