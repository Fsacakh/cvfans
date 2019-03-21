package com.auto.mocker;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.app.base.common.NameValuePair;
import com.app.mock.ArrayParamRequest;
import com.app.mock.BaseMocker;
import com.app.mock.SimpleParamRequest;

public class AutoGuaranteeControllerMocker extends BaseMocker 
{
	/**
	 * 车辆担保申请接口测试用例
	 */
	public void autoGuaranteeApply() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc, SimpleParamRequest.Method.Post, "/Persist/Auto/Guarantee/Insert.action");
		mocker.addParameter("autoId", "b43fc173c56d4abe8870b52c56de2659");
		mocker.addParameter("stationId", "b43fc173c56d4abe8870b52c56de2659");
		mocker.call();
	}
	
	/**
	 * 车辆担保审批
	 * @throws Exception
	 */
	public void autoGuaranteeAppr() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc, SimpleParamRequest.Method.Post, "/Persist/Auto/Guarantee/Update.action");
		mocker.addParameter("guaranteeId", "432386cc5bf4490992f122a678607415");
		mocker.addParameter("apprResult", 1);
		mocker.addParameter("apprComment", "jiushishih");
		mocker.call();
	}
	/**
	 * 
	 * 车辆担保查询
	 * @throws Exception
	 */
	public void autoGuaranteeSearch() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc, SimpleParamRequest.Method.Post, "/Query/Auto/Guarantee/Pagination.action");
		mocker.addParameter("statementId", "queryAutoGuaranteeInfos");
		NameValuePair [] parameters=new NameValuePair[3];
		parameters[0]=new NameValuePair("autoId","b43fc173c56d4abe8870b52c56de2659");
		parameters[1]=new NameValuePair("iDisplayStart","0");
		parameters[2]=new NameValuePair("iDisplayLength","2");
		mocker.addParameter("parameters", parameters);
		mocker.call();
	}
	
	/**
	 * 汽车担保取消
	 * @throws Exception
	 */
	@Test
	public  void autoGuaranteeDelete() throws Exception
	{
		ArrayParamRequest mocker=new ArrayParamRequest(this.mockMvc, ArrayParamRequest.Method.Post, "/Persist/Auto/Guarantee/Delete.action");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("guaranteeId", "432386cc5bf4490992f122a678607415");
		mocker.addParameter(map);
		mocker.call();
	}
}
