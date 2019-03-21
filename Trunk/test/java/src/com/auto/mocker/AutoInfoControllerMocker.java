package com.auto.mocker;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.app.base.common.NameValuePair;
import com.app.mock.ArrayParamRequest;
import com.app.mock.BaseMocker;
import com.app.mock.SimpleParamRequest;

/**
 * 汽车实体相关接口测试类
 * 
 * @author zhangsong
 * 
 */
public class AutoInfoControllerMocker extends BaseMocker {

	// 车辆注册接口测试用例
	public void AutoRegister() throws Exception {
		SimpleParamRequest mocker = new SimpleParamRequest(this.mockMvc,
				SimpleParamRequest.Method.Post, "/Persist/AutoInfo/Save.action");
		mocker.addParameter("brand", "北京现代2").addParameter("model", "轿车")
				.addParameter("Plate", "车牌类型")
				.addParameter("plateNo", "鄂J8989").addParameter("licenseNo",
						"34234").addParameter("licenseFile",
						"\\kjk\\lklk\\ee.txt").addParameter("engineNo", "发动机号")
				.addParameter("frameNo", "车架号").addParameter("ownerType", 1)
				.addParameter("ownerId", "6d00d5ad7afa45378a4508f0e07a2edb");
		mocker.call();

	}

	// 车辆注销接口测试用例
	public void AutoDelete() throws Exception {
		ArrayParamRequest mocker = new ArrayParamRequest(this.mockMvc,
				ArrayParamRequest.Method.Post,
				"/Persist/AutoInfo/Delete.action");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("autoId", "7dd259e872604d1c94e0df386190f11b");
		mocker.addParameter(map);
		mocker.call();
	}

	// 车辆查看接口测试用例
	public void AutoQuery() throws Exception {
//		RequestMocker mocker = new RequestMocker(this.mockMvc,
//				RequestMocker.Method.Post, "/Query/AutoInfo/List.action");
		SimpleParamRequest mocker = new SimpleParamRequest(this.mockMvc,
				SimpleParamRequest.Method.Post, "/Query/AutoInfo/Load.action");
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("ownerId", "6d00d5ad7afa45378a4508f0e07a2edb");
		map.put("plateNo", "鄂J8989");
		mocker.addParameter("parameters", map);
		mocker.addParameter("entity", "AutoInfo");
		mocker.call();
	}
	
	//车辆车讯接口测试用例
	@Test
	public void QueryList() throws Exception
	{
		SimpleParamRequest mocker = new SimpleParamRequest(this.mockMvc,
				SimpleParamRequest.Method.Post, "/Query/AutoInfo/Pagination.action");
		mocker.addParameter("statementId", "queryAutoInfos");
		NameValuePair [] parameters=new NameValuePair[3];
		parameters[0]=new NameValuePair("plateNo","鄂J8989");
		parameters[1]=new NameValuePair("iDisplayStart","0");
		parameters[2]=new NameValuePair("iDisplayLength","2");
		mocker.addParameter("parameters", parameters);
		mocker.call();
	}
}
