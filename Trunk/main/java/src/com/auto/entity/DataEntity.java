package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.Clause;
import com.app.base.entity.annotation.Where;
import com.app.base.sao.rdbms.Persist;

@Entity(name="DataInfo")
@Table(name="T_SYS_DATA")
public class DataEntity extends BaseEntity<String> {
	private static final long serialVersionUID = -448147432126111389L;
	
	@Id
	private  @Column(name="ID") String 	id;
	
	@Where({@Clause(persist=Persist.Query, required=true)})
	private  @Column(name="PARENT_ID")  String	parentId;
	private  @Column(name="NAME")  String	name;
	private  @Column(name="LABEL")  String 	label;
	private  @Column(name="VALUE")  String	value;
	
	public DataEntity() {
		super();
	}

	@Override
	public String getId() {
		return id;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public void setId(String id) {
		this.id = id;
	}
}
