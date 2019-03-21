package com.auto.entity;


public class AdminEntity extends BaseEntity<String> {
	private static final long serialVersionUID = 6748004869431122897L;
	private String userId;
	private String name="管理员";
	
	public AdminEntity() {
		super();
	}
	
	public AdminEntity(String userId){
		this.userId = userId;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getCreatedUserId() {
		return super.getCreatedUserId();
	}
}
