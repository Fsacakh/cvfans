package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="User")
@Table(name="T_SYS_USER_INFO")
/**
 * 用户实体类
 */
public class UserEntity extends  BaseEntity<String> {
	private static final long serialVersionUID = 6030843974599734232L;
	
	@Id
	private @Column(name="USER_ID") String userId; // userId 
	
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="USER_NAME") String userName; // userName
	private @Column(name="REAL_NAME") String realName;
	private @Column(name="PASSWORD") String password; // password 
	private @Column(name="USER_STATUS") Integer userStatus; // userStatus
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="OWNER_ID") String ownerId; // ownerId 
	private @Column(name="OWNER_ROLE") Integer ownerRole; // ownerRole  账号类型.1）司机。 2）服务站。 3）车管。 4）系统。
	private @Column(name="LOGIN_COUNT") Integer loginCount = 0; //异常登录次数
	
	private String code; //登录验证码
	
	private Object owner; //Owner_id对象
	
	public UserEntity(){}
	
	public UserEntity(String userId){
		this.userId = userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return this.userId;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return this.userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setPassword(String password){
		this.password = password;
	}
	
	@JsonIgnore
	public String getPassword(){
		return this.password;
	}
	
	//3次登录异常，账号锁定
	public Integer getUserStatus(){
		return this.loginCount == 3 ? 0 : 1;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}


	public void setOwnerId(String ownerId){
		this.ownerId = ownerId;
	}
	
	public String getOwnerId(){
		return this.ownerId;
	}

	public void setOwnerRole(Integer ownerRole){
		this.ownerRole = ownerRole;
	}
	
	public Integer getOwnerRole(){
		return this.ownerRole;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Object getOwner() {
		return owner;
	}


	public void setOwner(Object owner) {
		this.owner = owner;
	}


	public Integer getLoginCount() {
		return loginCount;
	}


	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}


	@Override
	public String getId() {
		return userId;
	}
}