package com.auto.hook;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.app.base.common.AppException;
import com.app.base.utils.StringUtils;
import com.app.components.persist.IPersistService;
import com.auto.common.impl.DefaultPersistHook;
import com.auto.entity.FavoriteInfoEntity;

@Component("FavoriteInfoPersistHooker")
public class FavoriteInfoPersistHooker  extends  DefaultPersistHook<FavoriteInfoEntity>{

	@Resource(name="PersistService")
	private IPersistService<FavoriteInfoEntity> persistService;
	
	@Override
	protected void preInsert(FavoriteInfoEntity entity) throws Exception {
		checkStationId(entity);
		
		super.preInsert(entity);
	}
	
	@Override
	protected  void preDelete(FavoriteInfoEntity entity) throws Exception {
		checkisdelete(entity);

		super.preDelete(entity);
	}
	
	private  void checkStationId(FavoriteInfoEntity  entity) throws Exception
	{
		String  stationid=entity.getStationId();
		String  userid=entity.getCreatedUserId();
		if(!StringUtils.isNullOrEmpty(stationid)&&!StringUtils.isNullOrEmpty(userid)){
			FavoriteInfoEntity favorentity =new   FavoriteInfoEntity();
			favorentity.setStationId(stationid);
			favorentity.setCreatedUserId(userid);
			favorentity = persistService.load(favorentity);
			if(favorentity !=null&&favorentity.getStationId().equals(stationid)){
				throw new AppException("您已经收藏该服务站。");
			}
		}
		
	}
	
	private  void  checkisdelete(FavoriteInfoEntity  entity) throws Exception
	{
		String   favoriteId= entity.getFavoriteId();
		if(!StringUtils.isNotNullOrEmpty(favoriteId)){
			FavoriteInfoEntity favorentity =new   FavoriteInfoEntity();
			favorentity.setFavoriteId(favoriteId);
			favorentity = persistService.load(favorentity);
			int is_delete = favorentity.getDeleted();
			if(is_delete==1){
				throw  new   AppException("该服务站收藏记录已被删除。");
			}
		}
	}
}
