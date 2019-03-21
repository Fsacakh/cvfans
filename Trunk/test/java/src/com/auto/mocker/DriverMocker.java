package com.auto.mocker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.base.common.PaginationResult;
import com.app.components.query.IQueryService;
import com.app.components.query.PaginationQueryRequest;
import com.auto.entity.PushInfoEntity;
import com.auto.service.impl.MessagePushServiceFacadeImpl;

/**
 * 司机相关接口测试类
 * @author zhangsong
 *
 */
public class DriverMocker 
{
	
	//司机信息注册测试用例
	public void DriverRegister() throws Exception
	{
		/*String birthday="2014-11-11";
		String licenseDate="1990-11-11";
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Date b=new Date();
		Date l=new Date();
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc, SimpleParamRequest.Method.Post , "/Persist/Driver/Insert.action");
		mocker.addParameter("name", "小桌子")
		.addParameter("nickname", "馒头")
		.addParameter("bgLogoFile", "hh\\jjj\\jjj.txt")
		.addParameter("logoFile", "hhh\\sdf\\\ttt.imag")
		.addParameter("bornDate",b)
		.addParameter("licenseNo","鄂A66576" )
		.addParameter("licenseDate", l)
		.addParameter("province", "上海")
		.addParameter("city", "上海")
		.addParameter("area", "上海市浦东新区")
		.addParameter("address", "上海市浦东新区XX街道");
		mocker.call();*/
		
		/*SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc, SimpleParamRequest.Method.Post , "/Persist/FavoriteInfo/Insert.action");
		mocker.addParameter("stationId", "432423424");
		mocker.call();*/
	}
	public static void main(String[] args) {
		// TODO set your appkey and master secret here
		try {
			//AndoridListcast  listcast =new  AndoridListcast();
			/*listcast.setAppMasterSecret("wkebaygwkunepap8s7adx1cjdzsnvoru");
			listcast.setPredefinedKeyValue("appkey", "5541867567e58e39a3004630");
			listcast.setPredefinedKeyValue("title", "今天有大福利");
			listcast.setPredefinedKeyValue("timestamp", Integer.toString((int)(System.currentTimeMillis() / 1000)));
			listcast.setPredefinedKeyValue("ticker","你有未读消息！");
			listcast.setPredefinedKeyValue("text","今天笨服务站提供免费食品");
			listcast.setPredefinedKeyValue("after_open","go_app");
			listcast.setPredefinedKeyValue("device_tokens", "AkvOq6wf7ZUfIr707UAf5JVH901s4cQs0DLEch8zimxO,AlZj-P1mgf_jehFnhizvV1YtWmB-W-QsGcAhbSQU3FDs");
			listcast.setPredefinedKeyValue("production_mode", "true");
			listcast.setPredefinedKeyValue("display_type", "notification");
			listcast.send();*/
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
