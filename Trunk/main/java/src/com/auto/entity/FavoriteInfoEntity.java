package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.PersistHook;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

/**
 * 服务站收藏实体bean
 */

@Entity(name="FavoriteInfo")
@Table(name="T_AUTO_FAVORITE_INFO")
@PersistHook("FavoriteInfoPersistHooker")
public class FavoriteInfoEntity  extends  BaseEntity<String>{
	private static final long serialVersionUID = -1150509463835504839L;
	
	@Id
	private  @Column(name="FAVORITE_ID") String  favoriteId;
	@Where({@Clause(persist=Persist.Query)})
	private  @Column(name="STATION_ID") String  stationId;
  
	public FavoriteInfoEntity() {
		super();
	}

	public String getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	@Override
	public String getId() {
		return favoriteId;
	}
	
	@Where({@Clause(persist=Persist.Query)})
	@Column(name="CREATED_USER_ID") 
	
	@Override
	public String getCreatedUserId() {
		return super.getCreatedUserId();
	}
	
	@Override
	public int getDeleted() {
		return 0;
	}
	
}
