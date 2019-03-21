package com.auto.mocker;

import org.junit.Test;

import com.app.mock.BaseMocker;
import com.app.mock.SimpleParamRequest;

/**
 * 服务站评论有关接口测试类
 * @author zhangsong
 *
 */
public class StationComentControllerMocker extends BaseMocker 
{

	//司机点评接口测试用例
	@Test
	public  void stationComent() throws Exception
	{
		SimpleParamRequest mocker=new SimpleParamRequest(this.mockMvc,SimpleParamRequest.Method.Post,"/Persist/Station/Comment/Insert.action");
		mocker.addParameter("driverId", "6d00d5ad7afa45378a4508f0e07a2edb")
		.addParameter("generalScore", 12.5)
		.addParameter("stationId", "687fd60fb0aa44ba909a472d919a149e")
		.addParameter("comment", "价格合理速度快!")
		.addParameter("qualityScore", 11)
		.addParameter("timeScore", 4)
		.addParameter("priceScore", 5);
		mocker.call();
	}
}
