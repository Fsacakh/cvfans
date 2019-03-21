/**
 * BaseEntity是项目中所有实体类的基础类，该基础类中包含了扩展实体类的一些公共特性。
 */
package com.auto.entity;

import java.util.Date;

import javax.persistence.Column;

import com.app.base.entity.Entity;
import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.LogicDelete;
import com.app.base.entity.annotation.PersistFacade;
import com.app.base.entity.annotation.PersistHook;
import com.app.base.entity.annotation.QueryHook;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@PersistHook("DefaultPersistHook")
@QueryHook("DefaultQueryHook")
@PersistFacade("PersistServiceFacade")
public abstract class BaseEntity<T> extends Entity<T>{
	private static final long serialVersionUID = -4160307228802570868L;
	private String 	createdUserId;
	private Date	createdDate;
	private String	updatedUserId;
	private Date	updatedDate;
	private int		deleted;
	
	public BaseEntity(){}
	
	@Column(name="IS_DELETED")
	@LogicDelete(value=1)
	@Where({@Clause(persist=Persist.Update, required=true),@Clause(persist=Persist.Query, required=true)})
	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Column(name="CREATED_USER_ID") 
	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}
	
	@Column(name="CREATED_DATE") 
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="UPDATED_USER_ID", updatable=true) 
	public String getUpdatedUserId() {
		return updatedUserId;
	}
	
	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	
	@Column(name="UPDATED_DATE", updatable=true) 
	public Date getUpdatedDate() {
		return updatedDate;
	}
	
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
