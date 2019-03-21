package com.auto.mocker;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.app.base.common.NameValuePair;
import com.app.mock.ArrayParamRequest;
import com.app.mock.BaseMocker;
import com.app.mock.SimpleParamRequest;

/**
 * 车管相关接口测试类
 * @author zhangsong
 *
 */
public class EnterpriseControllerMocker extends BaseMocker
{
	//车管查询测试样例
	@Test
	public  void enterpriseQuery() throws Exception
	{

		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc,SimpleParamRequest.Method.Post,"/Query/Enterprise/Pagination.action");
		mocker.addParameter("statementId", "queryEnterpriseInfos");
		
		NameValuePair[] parameters=new NameValuePair[3];
		parameters[0]=new NameValuePair("name","小王");
		parameters[1]=new NameValuePair("iDisplayStart","0");
		parameters[2]=new NameValuePair("iDisplayLength","3");
		mocker.addParameter("parameters", parameters);
		mocker.call();
	}
	
	//车管注册测试样例
	public void enterpriseRegister() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc,SimpleParamRequest.Method.Post, "/Persist/Enterprise/Save.action");
		mocker.addParameter("name", "小王")
		.addParameter("address", "上海")
		.addParameter("contacter", "小林")
		.addParameter("contactTel", "12323")
		.addParameter("finContacter", "小号")
		.addParameter("finContactMobile", "343434")
		.addParameter("licenseFile", "sdfdsf\\sdfsd\\tyty\\");
		mocker.call();
	}
	
	//车管注销测试样例
	public void enterpriseDlete() throws Exception
	{
		ArrayParamRequest mocker=new ArrayParamRequest(this.mockMvc, ArrayParamRequest.Method.Post, "/Persist/Enterprise/Delete.action");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("enterpriseId", "20118c0d74d3427eb8d9f82e8dea6d11");
		mocker.addParameter(map);
		mocker.call();
	}
}
