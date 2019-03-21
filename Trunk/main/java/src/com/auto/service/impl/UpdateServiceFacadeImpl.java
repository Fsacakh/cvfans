package com.auto.service.impl;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.auto.service.IUpdateService;
import com.auto.service.IUpdateServiceFacade;

@Service("UpdateServiceFacadeImpl")
public class UpdateServiceFacadeImpl  implements  IUpdateServiceFacade{
	@Resource(name="UpdateService")
	IUpdateService  updateService;
	
	@Override
	public void update(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		updateService.update(params);
	}
   
}
