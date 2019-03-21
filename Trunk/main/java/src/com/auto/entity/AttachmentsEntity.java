package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Attachments")
@Table(name="T_AUTO_ATTACHMENTS")
/**
 * 附件实体类
 */
public class AttachmentsEntity extends  BaseEntity<String> {
	
	private static final long serialVersionUID = 8334946001211714314L;
	@Id	
	private @Column(name="FILE_ID") String fileId; // fileId 
	private @Column(name="FILE_SUBJECT") String fileSubject; // fileSubject 
	private @Column(name="FILE_NAME") String fileName; // fileName 
	private @Column(name="FILE_TYPE") Integer fileType; // fileType 
	private @Column(name="FILE_USE") Integer fileUse; // fileUse 
	
	public AttachmentsEntity(){}
	
	public AttachmentsEntity(String id)
	{
		this.fileId=id;
	}

	public void setFileId(String fileId){
		this.fileId = fileId;
	}
	
	public String getFileId(){
		return this.fileId;
	}

	public void setFileSubject(String fileSubject){
		this.fileSubject = fileSubject;
	}
	
	public String getFileSubject(){
		return this.fileSubject;
	}

	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public String getFileName(){
		return this.fileName;
	}

	public void setFileType(Integer fileType){
		this.fileType = fileType;
	}
	
	public Integer getFileType(){
		return this.fileType;
	}

	public void setFileUse(Integer fileUse){
		this.fileUse = fileUse;
	}
	
	public Integer getFileUse(){
		return this.fileUse;
	}


	@Override
	public String getId() {
		return fileId;
	}
}