package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Entity(name="DriverAlbum")
@Table(name="T_AUTO_DRIVER_ALBUM")
/**
 * 司机相册实体类
 */
public class DriverAlbumEntity extends  BaseEntity<String> {
	
	private static final long serialVersionUID = 8542752570569058554L;
	@Id	
	private @Column(name="ALBUM_ID") String albumId; // albumId
	
	@Where({@Clause(persist=Persist.Query)})
	private @Column(name="DRIVER_ID") String driverId; // driverId 
	private @Column(name="ALBUM_URL") String url; // url 
	
	public DriverAlbumEntity(){}

	public DriverAlbumEntity(String albumId) {
		super();
		this.albumId = albumId;
	}


	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getId() {
		return albumId;
	}
}