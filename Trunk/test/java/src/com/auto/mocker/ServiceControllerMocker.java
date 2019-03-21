package com.auto.mocker;

import java.text.SimpleDateFormat;

import org.junit.Test;

import com.app.base.common.NameValuePair;
import com.app.mock.BaseMocker;
import com.app.mock.SimpleParamRequest;

/**
 * 汽车服务相关接口测试类
 * @author zhangsong
 *
 */
public class ServiceControllerMocker extends BaseMocker 
{
	//车辆服务记录添加接口测试用例
	public void serviceadd() throws Exception
	{
		SimpleParamRequest mocker = new SimpleParamRequest(this.mockMvc,SimpleParamRequest.Method.Post, "/Persist/AutoService/Insert.action");
		mocker.addParameter("driverId","b43fc173c56d4abe8870b52c56de2659" )
		.addParameter("autoId", "7dd259e872604d1c94e0df386190f11b")
		.addParameter("stationId", "734a0cd952f346dc8a66174752316247")
		.addParameter("enterDate", new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-11"))
		.addParameter("leaveDate", new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-11"))
		.addParameter("serviceDate", new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-11"))
		.addParameter("payMode", 8)
		.addParameter("serviceAmount",1000)
		.addParameter("paymentAmount", 900)
		.addParameter("patmentStatus", 8);
		mocker.call();
	}
	//车辆服务查询接口测试用例
	@Test
	public void autoServiceList() throws Exception
	{
		SimpleParamRequest mocker = new SimpleParamRequest(this.mockMvc,SimpleParamRequest.Method.Post, "/Query/AutoService/Pagination.action");
		mocker.addParameter("statementId", "queryAutoServiceInfos");
		NameValuePair [] parameters=new NameValuePair[3];
		parameters[0]=new NameValuePair("driverId", "b43fc173c56d4abe8870b52c56de2659");
		parameters[1]=new NameValuePair("iDisplayStart", "0");
		parameters[2]=new NameValuePair("iDisplayLength","1");
		mocker.addParameter("parameters", parameters);
		mocker.call();
	}
	//车辆服务点评
}
