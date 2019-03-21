package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Entity(name="Score")
@Table(name="T_AUTO_SCORE_INFO")
public class ScoreEntity extends  BaseEntity<String> {
	private static final long serialVersionUID = -3221566978652939353L;
	
	@Id
	private @Column(name="SCORE_ID") String scoreId; // scoreId 
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="OWNER_ID") String ownerId; // 积分所属主体ID 
	private @Column(name="OWNER_TYPE") Integer ownerType; // 1）司机积分。 2）车辆积分 
	private @Column(name="SCORE") Integer score; // score 
	private @Column(name="COMMENT") String comment; // comment 
	
	public ScoreEntity(){}
	

	public void setScoreId(String scoreId){
		this.scoreId = scoreId;
	}
	
	public String getScoreId(){
		return this.scoreId;
	}

	public void setOwnerId(String ownerId){
		this.ownerId = ownerId;
	}
	
	public String getOwnerId(){
		return this.ownerId;
	}

	public void setOwnerType(Integer ownerType){
		this.ownerType = ownerType;
	}
	
	public Integer getOwnerType(){
		return this.ownerType;
	}

	public void setScore(Integer score){
		this.score = score;
	}
	
	public Integer getScore(){
		return this.score;
	}

	public void setComment(String comment){
		this.comment = comment;
	}
	
	public String getComment(){
		return this.comment;
	}

	@Override
	public String getId() {
		return this.scoreId;
	}
}