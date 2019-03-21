package com.auto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jboss.logging.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.base.utils.KeyUtils;
import com.app.base.web.JsonParameter;
import com.app.base.web.common.DataFormResponse;
import com.app.base.web.common.DataListResponse;
import com.app.base.web.common.ResponseCode;
import com.app.base.web.controller.BaseController;
import com.app.components.persist.IPersistServiceFacade;
import com.app.components.query.DataQueryRequest;
import com.app.components.query.IQueryService;
import com.app.components.query.IQueryServiceFacade;
import com.app.components.query.QueryRequest;
import com.auto.entity.DriverEntity;
import com.auto.entity.PushInfoEntity;
import com.auto.entity.PushUserInfoEntity;
import com.auto.entity.UserEntity;
import com.auto.service.IUpdateService;
import com.auto.service.IUpdateServiceFacade;

@Controller
@RequestMapping("/Pushmessage")
public class PushMessageController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(PushMessageController.class);
	@Resource(name="PersistServiceFacade")
	private IPersistServiceFacade<PushInfoEntity> pushInfoServiceFacade = null;
	
	@Resource(name="PersistServiceFacade")
	private IPersistServiceFacade<PushUserInfoEntity> pushUserInfoServiceFacade = null;
	
	@Resource(name="QueryServiceFacade")
	private IQueryServiceFacade  queryServiceFacade;
	
    public   PushMessageController(){}
    
    /**
	 * 推送消息新增
	 * @param request
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/Add.*")
    public @ResponseBody DataFormResponse<PushInfoEntity>   addpushinfo(@JsonParameter("title") String title,@JsonParameter("content") String content,@JsonParameter("customerlist") String customerlist){
    	DataFormResponse<PushInfoEntity>  response =null;
    	String[]  customers = customerlist.split(",");
    	String  pushId =KeyUtils.generate();
    	try {
    		PushInfoEntity  pushinfo=new  PushInfoEntity();
    		pushinfo.setPushId(pushId);
        	pushinfo.setTitle(title);
        	pushinfo.setContent(content);
        	pushinfo.setStatus(0);
			pushInfoServiceFacade.insert(pushinfo);
			PushUserInfoEntity   pushuserinfo =new  PushUserInfoEntity(); 
			if(customers!=null){
				for(String  customer:customers){
					String  receiveId = KeyUtils.generate();
					pushuserinfo.setReceiveId(receiveId);
					pushuserinfo.setPushId(pushId);
					pushuserinfo.setUserId(customer);
					pushUserInfoServiceFacade.insert(pushuserinfo);
				}
			}
			response = new DataFormResponse<PushInfoEntity>(ResponseCode.Success.code, pushinfo);  
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			logger.error("AddPushInfo Exception.",exc);
			response = new DataFormResponse<PushInfoEntity>(exc);
		}
    	return  response;
    }
    
    
    /**
   	 * 查询已选择推送客户信息
   	 * @param request
   	 * @return
   	 * @throws Exception
   	 */
    @RequestMapping(value="/QueryCustomers.*")
    public  @ResponseBody DataListResponse<Map<String,Object>> querycustomers(@JsonParameter("pushId") String pushId){
    	DataListResponse<Map<String,Object>>  response =null;
    	DataQueryRequest request =new  DataQueryRequest(); 
    	request.setStatementId("cvfans.queryPushCustomerListInfos_selected");
    	request.setParameter("pushId", pushId);
    	try {
    		List<Map<String, Object>> list=queryServiceFacade.queryForList(request);
    		response = new DataListResponse<Map<String,Object>>(list);
		} catch (Exception exc) {
			logger.error("QueryCustomers Exception.",exc);
			response = new DataListResponse<Map<String,Object>>(exc);
		}
    	return  response;
    }
}
