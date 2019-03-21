package com.auto.entity;

import java.util.Date;

public class ServiceDtailsEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8889356999797140477L;
	
	private  String serviceCode; 
	private  String plateNo; 
	private  Date enterDate;
	private  Date leaveDate;
	private  String contacter;
	private  String contactTel;
	private  String provideServiceStation; 
	private  String guaranteeStation; 
	private  String serviceAmount; 
	private  String memberslevel; 
	private  String paymentAmount; 
	private  String serviceItems; 
	private  Integer paymentStatus; 
	private  String repairPart;

	
	
	public String getRepairPart() {
		return repairPart;
	}

	public void setRepairPart(String repairPart) {
		this.repairPart = repairPart;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getProvideServiceStation() {
		return provideServiceStation;
	}

	public void setProvideServiceStation(String provideServiceStation) {
		this.provideServiceStation = provideServiceStation;
	}

	public String getGuaranteeStation() {
		return guaranteeStation;
	}

	public void setGuaranteeStation(String guaranteeStation) {
		this.guaranteeStation = guaranteeStation;
	}

	public String getServiceAmount() {
		return serviceAmount;
	}

	public void setServiceAmount(String serviceAmount) {
		this.serviceAmount = serviceAmount;
	}

	public String getMemberslevel() {
		return memberslevel;
	}

	public void setMemberslevel(String memberslevel) {
		this.memberslevel = memberslevel;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getServiceItems() {
		return serviceItems;
	}

	public void setServiceItems(String serviceItems) {
		this.serviceItems = serviceItems;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer integer) {
		this.paymentStatus = integer;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
