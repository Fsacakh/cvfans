package com.auto.mocker;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.app.base.common.NameValuePair;
import com.app.mock.ArrayParamRequest;
import com.app.mock.BaseMocker;
import com.app.mock.SimpleParamRequest;

/**
 * 服务站折扣相关接口测试类
 * @author zhangsong
 *
 */
public class StationDiscountControllerMocker extends BaseMocker 
{
	
	//服务站折扣维护接口测试用例
	@Test
	public void discountWh() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc,SimpleParamRequest.Method.Post,"/Persist/Station/Discount/Save.action");
//		ArrayParamRequest mocker=new ArrayParamRequest(this.mockMvc,ArrayParamRequest.Method.Post,"/Persist/StationDiscount/Insert.action");
//		HashMap<String, Object> map=new HashMap<String, Object>();
//		mocker.addParameter("discountId", "4c59236fe8dd4590b6ef9552e0f41173");
		mocker.addParameter("stationId", "687fd60fb0aa44ba909a472d919a149e");
		mocker.addParameter("brand", "东风");
		mocker.addParameter("model", "");
		mocker.addParameter("autoLevel", 1333);
		mocker.addParameter("timeDiscount", 5);
		mocker.addParameter("materialDiscount", 6);
		mocker.call();
	}
	
	//服务站折扣查询接口测试用例
	public void discountQuery() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc,SimpleParamRequest.Method.Post,"/Query/Station/Discount/Pagination.action");

		mocker.addParameter("statementId","queryStationDiscountInfos");
		NameValuePair[] parameters = new NameValuePair[3];
		parameters[0]=new NameValuePair("stationId", "687fd60fb0aa44ba909a472d919a149e");
		parameters[1]=new NameValuePair("iDisplayStart", "0");
		parameters[2]=new NameValuePair("iDisplayLength", "2");
		mocker.addParameter("parameters", parameters);

		mocker.call();
	}
	
	//服务站折扣删除接口测试用例
	@Test
	public void discountDelete() throws Exception
	{
		ArrayParamRequest mocker=new ArrayParamRequest(this.mockMvc, ArrayParamRequest.Method.Post, "/Persist/Station/Discount/Delete.action");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("discountId", "6b2015c8fc724023bd86ac522e263c87");
		mocker.addParameter(map);
		mocker.call();
	}
}
