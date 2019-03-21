package com.auto.service;
import  com.auto.entity.*;

public interface IMessagePushService {
  
	 public  void   send(PushMessageEntity  message)  throws  Exception;

	boolean setPredefinedKeyValue(String key, Object value) throws Exception;

	boolean setExtraField(String key, String value) throws Exception;

	int send() throws Exception;

	void setTimestamp(String timestamp);
}
