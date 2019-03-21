package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.base.entity.annotation.PersistFacade;

/**
 * 故障类
 */

@Entity(name="Fault")
@Table(name="T_AUTO_FAULT_INFO")
@PersistFacade("FaultServiceFacade")
public class FaultEntity extends  BaseEntity<String> {
	private static final long serialVersionUID = -6738561670126165202L;

	@Id	
	private @Column(name="FAULT_ID") String faultId;  
	private @Column(name="FAULT_TITLE") String title;   
	private @Column(name="FAULT_URL") String url;  
	private @Column(name="FAULT_ICON")  String icon;
	private @Column(name="FAULT_TYPE") String type;  
	private @Column(name="FAULT_SOURCE") String source;  
	private @Column(name="FAULT_AUTHOR") String author;   
	private @Column(name="FAULT_EDITOR") String editor;  
	private @Column(name="COMMENT_COUNT") Integer commentCount;  
	private @Column(name="FAULT_CONTENT") String content;
	
	public FaultEntity(){}
	
	public FaultEntity(String faultId) {
		super();
		this.faultId = faultId;
	}

	public String getFaultId() {
		return faultId;
	}
	
	public void setFaultId(String faultId) {
		this.faultId = faultId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getEditor() {
		return editor;
	}
	
	public void setEditor(String editor) {
		this.editor = editor;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getCommentCount() {
		return commentCount;
	}
	
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public String getId() {
		return faultId;
	}
}