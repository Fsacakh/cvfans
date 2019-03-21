package com.auto.mocker;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.app.mock.ArrayParamRequest;
import com.app.mock.BaseMocker;
import com.app.mock.SimpleParamRequest;

/**
 * 服务站促销信息相关接口测试类
 * @author zhangsong
 *
 */
public class StationPromotionMocker extends BaseMocker 
{
	//服务站促销信息查询测试用例
	@Test
	public void StationPromotionQuery() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc,SimpleParamRequest.Method.Post,"/Query/StationPromotion/List.action");
		mocker.addParameter("entity", "StationPromotion");
		
		HashMap<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("stationId", "687fd60fb0aa44ba909a472d919a149e");
//		parameters.put("promotionId", "");
		mocker.addParameter("parameters", parameters);
		mocker.call();
	}
	
	//服务站促销信息新增测试用例
	public void StationPromotionAdd() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc,SimpleParamRequest.Method.Post,"/Persist/StationPromotion/Insert.action");
		mocker.addParameter("StationId","687fd60fb0aa44ba909a472d919a149e" );
		mocker.addParameter("title","老坛酸菜" );
		mocker.addParameter("content","红烧牛肉面炖着吃！" );
		mocker.addParameter("timeliness","10" );
		mocker.addParameter("isTop",0 );
		mocker.call();
	}
	//服务站促销信息删除测试用例
	public void StationPromotionDelete() throws Exception
	{
		ArrayParamRequest mocker=new ArrayParamRequest(this.mockMvc, ArrayParamRequest.Method.Post, "/Persist/StationPromotion/Delete.action");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("promotionId", "1e381f18cbeb4d51bd636747bc961961");
		mocker.addParameter(map);
		mocker.call();
	}
}
