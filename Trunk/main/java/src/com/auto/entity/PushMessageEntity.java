package com.auto.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushMessageEntity {
	private  String  title;
	private  String  content;
	private  List<Map<String,String>> devNoList = new ArrayList<Map<String,String>>();
	private  List<String>  receiveIdList = new  ArrayList<String>();
	public  PushMessageEntity(){} 
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Map<String, String>> getDevNoList() {
		return devNoList;
	}
	public void setDevNoList(List<Map<String, String>> devNoList) {
		this.devNoList = devNoList;
	}
	
	public void add(String devNo, String type){
		Map<String,String>  devnomap =new  HashMap<String,String>();
		devnomap.put(devNo,  type);
		
		devNoList.add(devnomap);
	}
	
    public  void addreceiveId(String  receiveId){
    	if(receiveId!=null){
    		receiveIdList.add(receiveId);
    	} 
    }
    
	public List<String> getReceiveIdList() {
		return receiveIdList;
	}

	public void setReceiveIdList(List<String> receiveIdList) {
		this.receiveIdList = receiveIdList;
	}
}
