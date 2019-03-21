package com.auto.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.LogicDelete;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Table(name="T_AUTO_PUSH_USER_INFO")
@Entity(name="PushUserInfo")
/**
 *推送对象实体类 
 */
public class PushUserInfoEntity  extends BaseEntity<String>{
            @Id
	private @Column(name= "RECEIVE_ID") String  ReceiveId;
	private @Column(name= "PUSH_ID")    String  PushId;
	private @Column(name= "USER_ID")    String  UserId;
	private @Column(name= "IS_SENT")    int	  	sent;
	private  @Column(name="ORDER_INDEX") Long index = null;
	
	private static final long serialVersionUID = 7835518315048899459L;

	public  PushUserInfoEntity(){} 
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return ReceiveId;
	}

	public String getReceiveId() {
		return ReceiveId;
	}

	public void setReceiveId(String receiveId) {
		ReceiveId = receiveId;
	}

	public String getPushId() {
		return PushId;
	}

	public void setPushId(String pushId) {
		PushId = pushId;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public Long getIndex(){
		if(index == null){
			return System.currentTimeMillis();
		}else{
			return index;
		}
	}
	
	public void setIndex(Long index){
		this.index = index;
	}
	
	public int getSent() {
		return sent;
	}
	public void setSent(int sent) {
		this.sent = sent;
	}
	public int getDeleted() {
		return 0;
	}

	public String getCreatedUserId() {
		return null;
	}

	public Date getCreatedDate() {
		return null;
	}
	
	public String getUpdatedUserId() {
		return null;
	}
	
	public Date getUpdatedDate() {
		return null;
	}
	
}
