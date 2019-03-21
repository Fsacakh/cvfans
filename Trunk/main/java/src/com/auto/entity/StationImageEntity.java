package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Entity(name="StationImage")
@Table(name="T_AUTO_STATION_IMG")
/**
 * 服务站实体类
 */
public class StationImageEntity extends  BaseEntity<String> {
	
	private static final long serialVersionUID = 8542752570569058554L;
	@Id	
	private @Column(name="IMG_ID") String imgId; // stationId
	
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="STATION_ID") String stationId; // parentId 
	private @Column(name="IMG_URL") String url; // name 
	private @Column(name="IMG_TITLE") String title; // province
	private @Column(name="IMG_MEMO") String memo; // province
	
	public StationImageEntity(){}

	public StationImageEntity(String imgId) {
		super();
		this.imgId = imgId;
	}

	
	

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String getId() {
		return imgId;
	}
}