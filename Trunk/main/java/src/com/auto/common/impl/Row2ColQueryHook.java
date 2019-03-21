package com.auto.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.app.components.query.DataQueryRequest;

@Component("Row2ColQueryHook")
public class Row2ColQueryHook extends DefaultQueryHook {
	public Row2ColQueryHook() {
	}

	@Override
	public void postQuery(DataQueryRequest request,	List<Map<String, Object>> results) 
	throws Exception {
		if(results == null || results.isEmpty()) return;
		
		super.postQuery(request, results);
		
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		int colNum = Integer.parseInt(request.getParameters().get("colNum").toString());
		int j = 0;
		for(int index = 0; index < results.size(); index += colNum){
			Map<String, Object> row = new HashMap<String, Object>();
			
			for(j = 0; j < colNum && index + j < results.size(); j++){
				row.put("COL_" + j, results.get(index + j));
			}
			
			rows.add(row);
		}
		
		results.clear();
		for(Map<String, Object> row : rows){
			results.add(row);
		}
	}
}
