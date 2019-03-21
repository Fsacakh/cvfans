package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 故障类
 */

@Entity(name="ServiceComplain")
@Table(name="T_AUTO_SERVICE_COMPLAIN_INFO")
public class ComplainEntity extends  BaseEntity<String> {
	private static final long serialVersionUID = -6738561670126165202L;

	@Id	
	private @Column(name="COMPLAIN_ID") String complainId;  
	private @Column(name="DRIVER_ID") String driverId;
	private @Column(name="STATION_ID") String stationId;
	private @Column(name="FILE_PATH") String filePath;
	private @Column(name="CATEGORY") String category;  
	private @Column(name="TITLE") String title;  
	private @Column(name="CONTENT")  String content;
	private @Column(name="SHEET_NO") String sheetNo;  
	private @Column(name="STATUS") String status = "-1";  
	private @Column(name="PROCESS_RESULT") String processResult;   
	private @Column(name="PROCESS_USER_ID") String processUserId;
	private @Column(name="PROCESS_USER_NAME") String processUserName;
	private @Column(name="PROCESS_DATE") Integer processDate;  
	
	public ComplainEntity(){}
	
	public ComplainEntity(String complainId) {
		super();
		this.complainId = complainId;
	}

	public String getComplainId() {
		return complainId;
	}

	public void setComplainId(String complainId) {
		this.complainId = complainId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	public String getProcessUserId() {
		return processUserId;
	}

	public void setProcessUserId(String processUserId) {
		this.processUserId = processUserId;
	}

	public String getProcessUserName() {
		return processUserName;
	}

	public void setProcessUserName(String processUserName) {
		this.processUserName = processUserName;
	}

	public Integer getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Integer processDate) {
		this.processDate = processDate;
	}

	@Override
	public String getId() {
		return complainId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}