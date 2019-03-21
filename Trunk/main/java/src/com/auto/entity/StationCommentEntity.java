package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.PersistFacade;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Entity(name="Comment")
@Table(name="T_AUTO_STATION_COMMENT")
/**
 * 服务站评价实体类
 */
@PersistFacade("ComentServiceFacade")
public class StationCommentEntity extends  BaseEntity<String> {
	
	private static final long serialVersionUID = -2649574298326519026L;
	@Id	
	private @Column(name="COMMENT_ID") String commentId; // commentId 
	private @Column(name="DRIVER_ID") String driverId; // driverId 
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="STATION_ID") String stationId; // stationId 
	private @Column(name="GENERAL_SCORE") Double generalScore; // generalScore 
	private @Column(name="PRICE_SCORE") Double priceScore; // priceScore 
	private @Column(name="QUALITY_SCORE") Double qualityScore; // qualityScore 
	private @Column(name="TIME_SCORE") Double timeScore; // timeScore 
	private @Column(name="BEHAVIOR_SCORE") Double behaviorScore; // timeScore 
	private @Column(name="COMMENT") String comment; // comment 
	
	public StationCommentEntity(){}
	
	public StationCommentEntity(String id)
	{
		this.commentId=id;
	}

	public void setCommentId(String commentId){
		this.commentId = commentId;
	}
	
	public String getCommentId(){
		return this.commentId;
	}

	public void setDriverId(String driverId){
		this.driverId = driverId;
	}
	
	public String getDriverId(){
		return this.driverId;
	}

	public void setStationId(String stationId){
		this.stationId = stationId;
	}
	
	public String getStationId(){
		return this.stationId;
	}

	public void setGeneralScore(Double generalScore){
		this.generalScore = generalScore;
	}
	
	public Double getGeneralScore(){
		return this.generalScore;
	}

	public void setPriceScore(Double priceScore){
		this.priceScore = priceScore;
	}
	
	public Double getPriceScore(){
		return this.priceScore;
	}

	public void setQualityScore(Double qualityScore){
		this.qualityScore = qualityScore;
	}
	
	public Double getQualityScore(){
		return this.qualityScore;
	}

	public void setTimeScore(Double timeScore){
		this.timeScore = timeScore;
	}
	
	public Double getTimeScore(){
		return this.timeScore;
	}

	public void setComment(String comment){
		this.comment = comment;
	}
	
	public String getComment(){
		return this.comment;
	}


	@Override
	public String getId() {
		return commentId;
	}

	public Double getBehaviorScore() {
		return behaviorScore;
	}

	public void setBehaviorScore(Double behaviorScore) {
		this.behaviorScore = behaviorScore;
	}
}