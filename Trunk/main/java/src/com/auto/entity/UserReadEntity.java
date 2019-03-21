package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Entity(name="UserRead")
@Table(name="T_SYS_USER_READ_INFO")
/**
 * 用户实体类
 */
public class UserReadEntity extends  BaseEntity<String> {
	private static final long serialVersionUID = 6030843974599734232L;
	
	@Id
	private @Column(name="READ_ID") String readId; // userId
	
	@Where({@Clause(persist=Persist.Query, required=false)})
	private @Column(name="USER_ID") String userId; // userId
	
	@Where({@Clause(persist=Persist.Query, required=false)})
	private @Column(name="OBJECT_ID") String objectId; // userName
	
	private @Column(name="OBJECT_TYPE") Integer objectType; // password 
	
	public UserReadEntity(){}
	
	public UserReadEntity(String userId){
		this.userId = userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return this.userId;
	}

	public String getReadId() {
		return readId;
	}

	public void setReadId(String readId) {
		this.readId = readId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Integer getObjectType() {
		return objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}

	@Override
	public String getId() {
		return readId;
	}
}