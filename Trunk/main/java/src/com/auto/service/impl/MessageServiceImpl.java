package com.auto.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.base.utils.BeanUtils;
import com.auto.service.IMessageService;
import com.cloopen.rest.sdk.CCPRestSmsSDK;

@Service("MessageServiceImpl")
public class MessageServiceImpl implements IMessageService {
	private static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	

	@Value("#{appconfig['msg.serviceUrl']}")
	private String serviceUrl = null;
	
	@Value("#{appconfig['msg.port']}")
	private String port = null;
	
	@Value("#{appconfig['msg.account.sid']}")
	private String account_sid = null;
	
	@Value("#{appconfig['msg.auth.token']}")
	private String auth_token= null;
	
	
	@Value("#{appconfig['msg.appId']}")
	private String appId = null;
	
	@Value("#{appconfig['msg.template']}")
	private String template = null;
	
	@Value("#{appconfig['msg.availableMtime']}")
	private String availableMtime = null;

	@Value("#{appconfig['msg.statusCode']}")
	private String  statusCode= null;
	
	@Override
	public String send(String mobileNo,String msg) throws Exception {
		Map<String, Object> result = null;
		try{
			//初始化SDK
			CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
			//restAPI.init("app.cloopen.com", "8883");  // *生产环境（用户应用上线使用） 
			restAPI.init(serviceUrl, port); 
			// ACCOUNT SID和AUTH TOKEN 
			restAPI.setAccount(account_sid,auth_token);
			restAPI.setAppId(appId);
			result = restAPI.sendTemplateSMS(mobileNo,template ,new String[]{msg,availableMtime});
			
			return result.get(statusCode).toString();
		}catch(Exception exc){
			logger.error("Send Exception.", exc);
			
			return "-1";
		}finally{
			StringBuffer sb = new StringBuffer();
			sb.append("Input: mobileNo=").append(mobileNo).append(", randomString=").append(msg);
			sb.append("\t\tOutput:").append(BeanUtils.writeValueAsString(result));
			logger.warn(sb.toString());
		}
	}
}
