package com.auto.mocker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.app.mock.BaseMocker;
import com.app.mock.SimpleParamRequest;

public class RelationControllerMocker extends BaseMocker{

	@Test
	public void update() throws Exception {
		SimpleParamRequest mocker = new SimpleParamRequest(this.mockMvc,
				SimpleParamRequest.Method.Post, "/RelationPersist/AutoInfo/Update.action");
		
		Map<String, Object> subject = new HashMap<String, Object>();
		subject.put("roleId", "11111");
		
		List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
		values.add(subject);
		values.add(subject);
		mocker.addParameter("subject", subject)
			  .addParameter("values", values);
		mocker.call();

	}
	
	public void insert() throws Exception {
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

}
