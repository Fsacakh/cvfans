package com.auto.common.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.components.query.DataQueryRequest;
import com.app.components.query.IQueryService;
import com.app.components.view.IValuePairServiceFacade;

@Component
public class DefaultValuePairServiceFacade implements IValuePairServiceFacade {
	@Autowired
	private IQueryService queryService;
	
	public DefaultValuePairServiceFacade() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getValuePairs(String statementId, String params)
	throws Exception {
		DataQueryRequest request = new DataQueryRequest();
		
		request.setStatementId(statementId);
		String[] parameters = params.split("[|]");
		if(parameters != null && parameters.length > 0){
			for(String parameter : parameters){
				request.setParameter(parameter.split(":")[0], parameter.split(":")[1]);
			}
		}
		
		List<Map<String, Object>> result = queryService.queryForList(request);
		return (List<T>) result;
	}
}