package com.auto.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.base.sao.rdbms.IObjectPersister;
import com.app.base.sao.rdbms.mapper.StatementMapper;
import com.app.base.sao.rdbms.mapper.StatementMapperContainer;
import com.auto.service.IUpdateService;

@Service("UpdateService")
public class UpdateServiceImpl  implements IUpdateService{
	@Resource(name="DataPersister")
	private IObjectPersister objectPersister;
	
	@Autowired
	public StatementMapperContainer statementMetaDataContainer;
	
	public  void  update(Map<String, Object>  params)  throws Exception{
		StatementMapper mapper = statementMetaDataContainer.getMapper("cvfans.updatePushInfos_isSent");
		List<String>  receiveIdList =(List<String>) params.get("receiveIds");
		for(String  receiveId :receiveIdList){
		  Map<String, Object>  param =new HashMap<String, Object>();
		  param.put("receiveId", receiveId);
		  objectPersister.update(statementMetaDataContainer.getStatement(mapper, param), param);
		}
	}
}
