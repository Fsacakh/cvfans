package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Entity(name="PushInfo")
@Table(name="T_AUTO_PUSH_INFO")
/**
 * 推送消息内容实体
 */
public class PushInfoEntity   extends  BaseEntity<String>{
	private static final long serialVersionUID = 3106239377798945232L;
	
	@Id
	@Where({@Clause(persist=Persist.Update, required=false)})
	private  @Column(name="PUSH_ID") String   pushId;
	private  @Column(name="TITLE")   String   title;
	private  @Column(name="CONTENT") String   content;
	private  @Column(name="STATUS")  int      status;
	public PushInfoEntity(){}
	@Override
	public String getId() {
		return pushId;
	}
	public String getPushId() {
		return pushId;
	}
	public void setPushId(String pushId) {
		this.pushId = pushId;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
