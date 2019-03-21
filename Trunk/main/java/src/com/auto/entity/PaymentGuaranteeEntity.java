package com.auto.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.PersistFacade;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Entity(name="PaymentGuarantee")
@Table(name="T_AUTO_PAYMENT_GUARANTEE_INFO")
@PersistFacade("PaymentGuaranteeServiceFacade")
public class PaymentGuaranteeEntity extends  BaseEntity<String> {
	private static final long serialVersionUID = -9054458790917215748L;
	@Id
	private @Column(name="GUARANTEE_ID") String guaranteeId; // guaranteeId 
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="SERVICE_ID") String serviceId; // autoId 
	private @Column(name="STATION_ID") String stationId; // stationId 
	private @Column(name="APPL_USER_ID") String applUserId; // applUserId 
	private @Column(name="APPL_DATE") Date applDate; // applDate 
	private @Column(name="APPL_COMMENT") String applComment; // applComment 
	private @Column(name="APPR_USER_ID") String apprUserId; // apprUserId 
	private @Column(name="APPR_DATE") Date apprDate; // apprDate 
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="APPR_RESULT") Integer apprResult; // apprResult 
	private @Column(name="APPR_COMMENT") String apprComment; // apprComment 
	private @Column(name="CANC_USER_ID") String cancUserId; // cancUserId 
	private @Column(name="CANC_DATE") Date cancDate; // cancDate 
	private @Column(name="CANC_COMMENT") String cancComment; // cancComment 
	
	public PaymentGuaranteeEntity(){}
	
	public PaymentGuaranteeEntity(String guaranteeId)
	{
		this.guaranteeId=guaranteeId;
	}
	
	public void setGuaranteeId(String guaranteeId){
		this.guaranteeId = guaranteeId;
	}
	
	public String getGuaranteeId(){
		return this.guaranteeId;
	}

	public void setStationId(String stationId){
		this.stationId = stationId;
	}
	
	public String getStationId(){
		return this.stationId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setApplUserId(String applUserId){
		this.applUserId = applUserId;
	}
	
	public String getApplUserId(){
		return this.applUserId;
	}

	public void setApplDate(Date applDate){
		this.applDate = applDate;
	}
	
	public Date getApplDate(){
		return this.applDate;
	}

	public void setApplComment(String applComment){
		this.applComment = applComment;
	}
	
	public String getApplComment(){
		return this.applComment;
	}

	public void setApprUserId(String apprUserId){
		this.apprUserId = apprUserId;
	}
	
	public String getApprUserId(){
		return this.apprUserId;
	}

	public void setApprDate(Date apprDate){
		this.apprDate = apprDate;
	}
	
	public Date getApprDate(){
		return this.apprDate;
	}

	public void setApprResult(Integer apprResult){
		this.apprResult = apprResult;
	}
	
	public Integer getApprResult(){
		return this.apprResult;
	}

	public void setApprComment(String apprComment){
		this.apprComment = apprComment;
	}
	
	public String getApprComment(){
		return this.apprComment;
	}

	public void setCancUserId(String cancUserId){
		this.cancUserId = cancUserId;
	}
	
	public String getCancUserId(){
		return this.cancUserId;
	}

	public void setCancDate(Date cancDate){
		this.cancDate = cancDate;
	}
	
	public Date getCancDate(){
		return this.cancDate;
	}

	public void setCancComment(String cancComment){
		this.cancComment = cancComment;
	}
	
	public String getCancComment(){
		return this.cancComment;
	}


	@Override
	public String getId() {
		return guaranteeId;
	}
}