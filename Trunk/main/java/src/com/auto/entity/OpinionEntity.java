package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Opinion")
@Table(name="T_AUTO_OPINION")

/**
 * 用户意见反馈实体类
 */
public class OpinionEntity extends BaseEntity<String> 
{
	private static final long serialVersionUID = 5354276371159797137L;
	
	@Id	
	private @Column(name="OPINION_ID") String opinionId; // opinionId 
	private @Column(name="CONTACT") String contact; // contact 
	private @Column(name="CONTENT") String content; // content 
	
	public OpinionEntity(){}
	
	public OpinionEntity(String opinionId)
	{
		this.opinionId=opinionId;
	}
	
	public String getOpinionId() {
		return opinionId;
	}

	public void setOpinionId(String opinionId) {
		this.opinionId = opinionId;
	}

	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String getId() {
		return opinionId;
	}
}
