package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Table(name="T_SYS_DEVICE_INFO")
@Entity(name="DeviceInfo")

/**
 * 设备信息实体
 */
public class DeviceInfoEntity  extends BaseEntity<String>{
	private static final long serialVersionUID = 722994190625712564L;
	
	@Id
    private @Column(name="DEV_ID")String  devId;
    
    @Where({@Clause(persist=Persist.Query)})
    private @Column(name="DEV_NO")String  devNo;
    
    @Where({@Clause(persist=Persist.Query)})
    private @Column(name="DEV_TYPE")String  devType;
    
    private @Column(name="USER_ID")String  userId;
    
    public  DeviceInfoEntity(){} 
    
	@Override
	public String getId() {
		return devId;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getDevNo() {
		return devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	


}
