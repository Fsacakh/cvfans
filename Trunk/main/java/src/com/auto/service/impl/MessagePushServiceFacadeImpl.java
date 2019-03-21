package com.auto.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.base.common.PaginationResult;
import com.app.components.persist.IPersistService;
import com.app.components.query.IQueryService;
import com.app.components.query.PaginationQueryRequest;
import com.auto.entity.PushInfoEntity;
import com.auto.entity.PushMessageEntity;
import com.auto.service.IMessagePushService;
import com.auto.service.IUpdateService;


/*
 * 推送消息门窗
 * @author licb
 */
@Service("MessagePushServiceFacadeImpl")
public class MessagePushServiceFacadeImpl  implements  InitializingBean{
	private static Log logger = LogFactory.getLog(MessagePushServiceFacadeImpl.class);
	private ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
	
	@Autowired
	private IQueryService queryService;
	
	@Autowired
	private IUpdateService updateservice;
	
	@Autowired
	private IPersistService<PushInfoEntity> persistService;
	
	@Resource(name="MessagePushServiceImpl")
	private IMessagePushService  messagePushService;
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Runnable imageScaleRunnable = new Runnable() {
	      public void run() {
	    	  try{
	    		  List<Map<String, Object>> messagelist = queryMessagePushInfoes(); //查询可发送推送消息
	    		  
	    		  ArrayList<String>  pushIds =new  ArrayList<String>();
	    		  if(messagelist != null&&messagelist.size()!=0){
    				 Map<String,PushMessageEntity>  pushMessages =new HashMap<String,PushMessageEntity>();
    				 String pushId = null;
    				 PushMessageEntity entity = null;
    				 String receiveId = null;
    				 //将消息按照消息编号和设备类型分组
    				 for(Map<String, Object> message : messagelist){
    					 pushId = message.get("PUSH_ID").toString();
    					 entity = pushMessages.get(pushId);
    					 if(entity == null){
    						 entity = new PushMessageEntity();
    						 entity.setTitle(message.get("TITLE").toString());
    						 entity.setContent(message.get("CONTENT").toString());
    					 }
    					 receiveId=message.get("RECEIVE_ID").toString();
    					 if(!entity.getReceiveIdList().contains(receiveId)){
    						 entity.addreceiveId(receiveId);
    					 }
    					 entity.add(message.get("DEV_NO").toString(), message.get("DEV_TYPE").toString());
    					 pushMessages.put(pushId,  entity);
    				 }
    				 
    				 //消息发送
    				 Collection<PushMessageEntity> cPushMessages = pushMessages.values();
    				 Map<String, Object> params = new HashMap<String, Object>();
    				 for(PushMessageEntity e : cPushMessages){
    					 messagePushService.setTimestamp(Integer.toString((int) (System.currentTimeMillis() / 1000)));
	    				 messagePushService.send(e);
	    				 //设置发送标志
	    				 params.put("receiveIds", e.getReceiveIdList());
	    				 updateservice.update(params);
    				 }
	    			   
	    		  }
	    		  if(logger.isInfoEnabled()){
	    			  logger.info(pushIds == null ? 0 : pushIds.size() + " messages will be sent........");
	    		  }
	    	  }catch(Exception exc){
	    		  logger.error("Push Exception.", exc);
	    	  }
	      }
	    };
	    
		service.scheduleWithFixedDelay(imageScaleRunnable, 60, 10, TimeUnit.SECONDS);
	}
	
	private List<Map<String, Object>> queryMessagePushInfoes() throws Exception {
		 PaginationQueryRequest request =new  PaginationQueryRequest();
		 request.setStatementId("cvfans.queryPushInfos");   
		 request.setParameter("iDisplayStart", 0);
		 request.setParameter("iDisplayLength", 400);
		 PaginationResult<List<Map<String, Object>>>  pagresult =  queryService.queryForPagination(request);
		 List<Map<String, Object>>  result =pagresult.getList();
		return result;
	}
}
