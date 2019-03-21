package com.auto.mocker;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.app.base.common.NameValuePair;
import com.app.base.utils.BeanUtils;
import com.app.mock.ArrayParamRequest;
import com.app.mock.BaseMocker;
import com.app.mock.MultipartParamRequest;
import com.app.mock.SimpleParamRequest;
import com.auto.entity.StationEntity;

/**
 * 服务站相关接口测试类
 * @author zhangsong
 *
 */
public class StationControllerMocker extends BaseMocker
{
	

	//服务站注册测试用例
	@Test
	public void stationRegister() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc, SimpleParamRequest.Method.Post, "/Persist/Station/Save.action");
		mocker.addParameter("name","wwww" )
		.addParameter("brand", "东风2222")
		.addParameter("province", "上海2222")
		.addParameter("city", "上海2222")
		.addParameter("area", "上海浦东新区2222")
		.addParameter("address", "安亭XX站")
		.addParameter("logoFile", "C:\\Users\\zhangsong\\test.txt")
		.addParameter("isMember", 1)
		.addParameter("contacter", "小李3")
		.addParameter("contactTel", "124343")
//		.addParameter("finContacter", "13232")
//		.addParameter("finContactTel", "3242424")
		.addParameter("fax", "4343")
		.addParameter("accountBank", "工商银行")
		.addParameter("accountNo", "45464698")
		.addParameter("licenseFile", "鄂A8878787")
		.addParameter("qualification", 3)
		.addParameter("serviceMemo", "东风，科技科技")
		.addParameter("briefIntro", "234343dfsdf");
		mocker.call();
	}
	
	//服务站注册测试用例 （含附件）
	public void stationRegisterF() throws Exception
	{
		MultipartParamRequest mocker=new MultipartParamRequest(this.mockMvc, "/MultipartPersist/Station/Insert.action");
		mocker.addFile("logoFile", "01.txt", null, new FileInputStream("H:/test/01.txt"));
		mocker.addFile("licenseFile", "02.txt", null, new FileInputStream("H:/test/02.txt"));
		
		StationEntity station=new StationEntity();
		station.setName("服务站");
		station.setBrand("东风");
		station.setProvince("江西");
		station.setCity("上海");
		station.setArea("上海浦东新区");
		station.setAddress("安亭XX站");
		station.setContacter("小王");
		station.setContactTel("3434343");
		station.setFax("4545");
		station.setAccountBank("工商银行");
		station.setAccountNo("3434");
		station.setServiceMemo("erer");
		station.setBriefIntro("34343");
		mocker.addParameter("data",BeanUtils.writeValueAsString(station));
		
		mocker.call();
	}
	
	
	//服务站注销测试用例
	public void stationDelete() throws Exception
	{
		ArrayParamRequest mocker=new ArrayParamRequest(this.mockMvc, ArrayParamRequest.Method.Post, "/Persist/Station/Delete.action");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("stationId", "7c1409b2ae6c4858a11c640696a136a5");
		mocker.addParameter(map);
		mocker.call();
	}
	
	//服务站查询测试样例
	public void queryStationList() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc,SimpleParamRequest.Method.Post,"/Query/Station/Pagination.action");
		mocker.addParameter("statementId", "queryStationInfos");
		
		NameValuePair[] parameters=new NameValuePair[3];
		parameters[0]=new NameValuePair("name","上海服务站");
		parameters[1]=new NameValuePair("iDisplayStart","0");
		parameters[2]=new NameValuePair("iDisplayLength","3");
		mocker.addParameter("parameters", parameters);
		mocker.call();
	}
}
