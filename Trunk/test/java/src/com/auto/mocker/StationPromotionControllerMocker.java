package com.auto.mocker;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.app.base.common.NameValuePair;
import com.app.mock.ArrayParamRequest;
import com.app.mock.BaseMocker;
import com.app.mock.SimpleParamRequest;

/**
 * 服务站促销信息相关接口测试类
 * @author zhangsong
 *
 */
public class StationPromotionControllerMocker extends BaseMocker 
{
	//服务站促销查询测试样例
	@Test
	public void StationPromotionQuery() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc,SimpleParamRequest.Method.Post,"/Query/Station/Promotion/Pagination.action");
		mocker.addParameter("statementId", "queryStationPromotionInfos");
		
		NameValuePair[] parameters=new NameValuePair[3];
		parameters[0]=new NameValuePair("stationId","1e381f18cbeb4d51bd636747bc961961");
		parameters[1]=new NameValuePair("iDisplayStart","0");
		parameters[2]=new NameValuePair("iDisplayLength","3");
		mocker.addParameter("parameters", parameters);
		mocker.call();
	}
	
	//服务站促销信息维护测试用例
	public void StationPromotionAdd() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc,SimpleParamRequest.Method.Post,"/Persist/Station/Promotion/Save.action");
		mocker.addParameter("StationId","687fd60fb0aa44ba909a472d919a149e" );
		mocker.addParameter("title","老坛酸菜" );
		mocker.addParameter("content","红烧牛肉面炖着吃！" );
		mocker.addParameter("timeliness","115" );
		mocker.addParameter("isTop",0 );
		mocker.call();
	}
	//服务站促销信息删除测试用例
	@Test
	public void StationPromotionDelete() throws Exception
	{
		ArrayParamRequest mocker=new ArrayParamRequest(this.mockMvc, ArrayParamRequest.Method.Post, "/Persist/Station/Promotion/Delete.action");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("promotionId", "b6fe1d3f8e464cbb9dba42b3b9681adb");
		mocker.addParameter(map);
		mocker.call();
	}
}
