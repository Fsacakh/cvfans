package com.auto.common.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.app.components.query.DataQueryRequest;
import com.app.components.query.IQueryService;

@Service("/station/StationSearch.html")
public class StationSearchViewServiceFacade extends  DefaultViewServiceFacadeImpl{
	@Resource(name="QueryService")
	private IQueryService queryService;
	
	private String[] sorts = new String[]{"ABCDE","FGHIJ","KLMNO","PQRST","UVWXYZ"};
	
	public StationSearchViewServiceFacade() {
	}

	@Override
	public void initialize(String view, ModelMap map, HttpServletRequest request)
	throws Exception {
		super.initialize(view, map, request);
		
		DataQueryRequest queryRequest = new DataQueryRequest();
		queryRequest.setStatementId("cvfans.queryProvinceInfoes");
		List<Map<String, Object>>results = queryService.queryForList(queryRequest);
		Map<String, List<Map<String, Object>>> provinces = new TreeMap<String, List<Map<String, Object>>>(new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		String value = null;
		List<Map<String, Object>> values = null;
		for(Map<String, Object> result : results){
			value = result.get("VALUE").toString().substring(0,1);
			
			for(String sort : sorts){
				if(sort.contains(value)){
					values = provinces.get(sort);
					if(values == null){
						values = new ArrayList<Map<String, Object>>();
					}
					values.add(result);
					provinces.put(sort, values);

					break;
				}
			}
		}
		map.put("provinces", provinces);
		
		queryRequest.clear();
		queryRequest.setStatementId("cvfans.queryAutoBrandInfoes");
		results = queryService.queryForList(queryRequest);
		Map<String, List<Map<String, Object>>> brands = new TreeMap<String, List<Map<String, Object>>>(new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		for(Map<String, Object> result : results){
			value = result.get("VALUE").toString().substring(0,1);
			
			for(String sort : sorts){
				if(sort.contains(value)){
					values = brands.get(sort);
					if(values == null){
						values = new ArrayList<Map<String, Object>>();
					}
					values.add(result);
					brands.put(sort, values);

					break;
				}
			}
		}
		map.put("brands", brands);
	}
}
