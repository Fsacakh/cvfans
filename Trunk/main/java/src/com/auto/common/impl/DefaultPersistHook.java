package com.auto.common.impl;

import java.util.Date;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.base.entity.Entity;
import com.app.base.entity.annotation.Validator;
import com.app.base.entity.annotation.Validators;
import com.app.base.sao.rdbms.IPersistHook;
import com.app.base.sao.rdbms.Persist;
import com.app.base.sao.rdbms.mapper.ColumnMetaData;
import com.app.base.sao.rdbms.mapper.EntityMetaData;
import com.app.base.sao.rdbms.mapper.IEntityMetaDataContainer;
import com.app.base.utils.KeyUtils;
import com.app.base.utils.StringUtils;
import com.auto.UserManager;
import com.auto.entity.BaseEntity;

@SuppressWarnings("rawtypes")
@Component("DefaultPersistHook")
public class DefaultPersistHook<T extends Entity> implements IPersistHook<T> {
	@Autowired
	private IEntityMetaDataContainer entityMetaDataContainer;
	
	public DefaultPersistHook() {
	}

	@Override
	public void prePersist(T entity, Persist persist) throws Exception {
		switch(persist){
			case Insert:
				preInsert(entity);
				break;
			case Update:
				preUpdate(entity);
				break;
			case Delete:
				preDelete(entity);
				break;
			default:
				throw new Exception("Unsupport persist parameter.");
		}
	}

	@Override
	public void postPersist(T entity, Persist persist) throws Exception {
		switch(persist){
			case Insert:
				postInsert(entity);
				break;
			case Update:
				postUpdate(entity);
				break;
			case Delete:
				postDelete(entity);
				break;
			default:
				throw new Exception("Unsupport persist parameter.");
		}
	}
	
	@SuppressWarnings({"unchecked" })
	protected void preInsert(T entity) throws Exception {
		Class<?> clazz = entity.getClass();
		EntityMetaData entityMetaData = entityMetaDataContainer.getMetaData(clazz);
		
		ColumnMetaData[] columnMetaDatas = entityMetaData.getColumns();
		for(ColumnMetaData columnMetaData : columnMetaDatas){
			Validators validators = columnMetaData.getValidators();
			if(validators != null){
				for(Validator validator : validators.value()){
					validator.constraint().getValidator().validate(validator, entity, columnMetaData, Persist.Insert);
				}
			}
		}
		
		//设置关键字值
		if(entityMetaData.getPrimaryKeys().length == 1){
			String pk = entityMetaData.getPrimaryKey().getField();
			
			BeanWrapper bw = new BeanWrapperImpl(entity);
			Object value = bw.getPropertyValue(pk);
			if(value == null || value.toString().equals("")){
				bw.setPropertyValue(pk, KeyUtils.generate());
			}
		}
		
		BaseEntity baseEntity = (BaseEntity) entity;
		Date now = new Date();
		String userId = UserManager.getUserId();
		if(StringUtils.isNotNullOrEmpty(userId)){
			baseEntity.setUpdatedUserId(userId);
			baseEntity.setCreatedUserId(UserManager.getUserId());
		}
		
		baseEntity.setCreatedDate(now);
		baseEntity.setUpdatedDate(now);
		baseEntity.setDeleted(0);
	}
	
	@SuppressWarnings({"unchecked" })
	protected void preUpdate(T entity) throws Exception{
		Class<?> clazz = entity.getClass();
		EntityMetaData entityMetaData = entityMetaDataContainer.getMetaData(clazz);
		
		ColumnMetaData[] columnMetaDatas = entityMetaData.getColumns();
		for(ColumnMetaData columnMetaData : columnMetaDatas){
			Validators validators = columnMetaData.getValidators();
			if(validators != null){
				for(Validator validator : validators.value()){
					validator.constraint().getValidator().validate(validator, entity, columnMetaData, Persist.Update);
				}
			}
		}
		
		BaseEntity baseEntity = (BaseEntity) entity;
		Date now = new Date();
		baseEntity.setUpdatedDate(now);
		String userId = UserManager.getUserId();
		if(StringUtils.isNotNullOrEmpty(userId)){
			baseEntity.setUpdatedUserId(userId);
		}
	}
	
	@SuppressWarnings({"unchecked" })
	protected void preDelete(T entity) throws Exception{
		Class<?> clazz = entity.getClass();
		EntityMetaData entityMetaData = entityMetaDataContainer.getMetaData(clazz);
		
		ColumnMetaData[] columnMetaDatas = entityMetaData.getColumns();
		for(ColumnMetaData columnMetaData : columnMetaDatas){
			Validators validators = columnMetaData.getValidators();
			if(validators != null){
				for(Validator validator : validators.value()){
					validator.constraint().getValidator().validate(validator, entity, columnMetaData, Persist.Delete);
				}
			}
		}
		
		BaseEntity baseEntity = (BaseEntity) entity;
		Date now = new Date();
		baseEntity.setUpdatedDate(now);
		baseEntity.setDeleted(1);
		String userId = UserManager.getUserId();
		if(StringUtils.isNotNullOrEmpty(userId)){
			baseEntity.setUpdatedUserId(userId);
		}
	}
	
	protected void postInsert(T entity) throws Exception {
	}
	
	protected void postUpdate(T entity) throws Exception{
	}
	
	protected void postDelete(T entity) throws Exception{
	}
}
