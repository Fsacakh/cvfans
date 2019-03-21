package com.auto.hook;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.app.components.persist.IPersistService;
import com.app.components.query.QueryRequest;
import com.auto.UserManager;
import com.auto.common.impl.DefaultQueryHook;
import com.auto.entity.UserReadEntity;

@Component("StationLoadHooker")
public class StationLoadHooker extends DefaultQueryHook {
	@Resource(name="PersistService")
	IPersistService<UserReadEntity> userReadService = null;
	
	public StationLoadHooker() {
	}

	@Override
	public void preQuery(QueryRequest request) throws Exception {
		if(! "".equals(UserManager.getUserId())) {
			String stationId = request.getParameters().get("stationId").toString();
			
			UserReadEntity read = new UserReadEntity();
			read.setObjectId(stationId);
			read.setUserId(UserManager.getUserId());
			read.setObjectType(1);
			UserReadEntity readed = userReadService.load(read);
			if(readed == null){
				userReadService.insert(read);
			}else{
				userReadService.update(readed);
			}
		}
		
		super.preQuery(request);
	}
}
