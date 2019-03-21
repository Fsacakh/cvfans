package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Entity(name="DeviceVersion")
@Table(name="T_SYS_DEV_VERSION")
/**
 * app版本更新实体类
 */
public class DeviceVersionEntity extends  BaseEntity<String> {
	private static final long serialVersionUID = -1315551978318425829L;
	@Id	
	private @Column(name="VERSION_ID") String versionId; 
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="DEV_TYPE") String devType; // licenseNo Android IOS
	private @Column(name="VERSION_NO") String versionNo; 
	private @Column(name="VERSION_PATH") String versionPath; 
	private @Column(name="MEMO") String memo;
	private @Column(name="OPTIONAL_VERSION_NO") String optionalVersionNo; 
	private @Column(name="REQUIRED_VERSION_NO") String requiredVersionNo; 
	private @Column(name="VERSION_SIZE") Double versionSize; 
	
	private int required = 0;
	private String message = "";

	public DeviceVersionEntity(){}
	
	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getVersionPath() {
		return versionPath;
	}

	public void setVersionPath(String versionPath) {
		this.versionPath = versionPath;
	}

	public String getOptionalVersionNo() {
		return optionalVersionNo;
	}

	public void setOptionalVersionNo(String optionalVersionNo) {
		this.optionalVersionNo = optionalVersionNo;
	}

	public String getRequiredVersionNo() {
		return requiredVersionNo;
	}

	public void setRequiredVersionNo(String requiredVersionNo) {
		this.requiredVersionNo = requiredVersionNo;
	}

	public int getRequired() {
		return required;
	}

	public void setRequired(int required) {
		this.required = required;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String getId() {
		return versionId;
	}

	public Double getVersionSize() {
		return versionSize;
	}

	public void setVersionSize(Double versionSize) {
		this.versionSize = versionSize;
	}
	
}