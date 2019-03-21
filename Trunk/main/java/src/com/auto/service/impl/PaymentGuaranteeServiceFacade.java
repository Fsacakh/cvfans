package com.auto.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.base.common.AppException;
import com.app.base.utils.DateUtils;
import com.app.base.web.RequestContext;
import com.app.components.persist.IPersistService;
import com.app.components.persist.PersistServiceFacadeImpl;
import com.auto.UserManager;
import com.auto.entity.PaymentGuaranteeEntity;
import com.auto.entity.ServiceEntity;

@Service("PaymentGuaranteeServiceFacade")
public class PaymentGuaranteeServiceFacade extends PersistServiceFacadeImpl<PaymentGuaranteeEntity> {
	private static Logger logger = LoggerFactory.getLogger(PaymentGuaranteeServiceFacade.class);
	
	@Autowired
	private IPersistService<ServiceEntity> servicePersistService;

	@Override
	public void insert(PaymentGuaranteeEntity entity) throws Exception {
		entity.setApplUserId(UserManager.getUserId());
		entity.setApplDate(DateUtils.getCurrentDate());
		
		ServiceEntity service = new ServiceEntity(entity.getServiceId());
		service = servicePersistService.load(service);
		if(service == null){
			logger.warn("No service info found for serviceId=" + entity.getServiceId());
			
			throw new AppException("担保的维修单不存在。");
		}
		
		service.setGuaranteeStatus(1);
		service.setGuaranteeStationId(entity.getStationId());
		servicePersistService.update(service);
		
		super.insert(entity);
	}
	
	@Override
	public void update(PaymentGuaranteeEntity entity) throws Exception {
		super.update(entity);
		
		String url = RequestContext.getRequestURI();
		if(url.contains("/Persist/Approve")){
			ServiceEntity service = new ServiceEntity(entity.getServiceId());
			service = servicePersistService.load(service);
			if(service == null){
				logger.warn("No service info found for serviceId=" + entity.getServiceId());
				
				throw new AppException("担保的维修单不存在。");
			}
			if(entity.getApprResult() == 2){
				service.setGuaranteeStationId(entity.getStationId());
			}else{
				service.setGuaranteeStationId("");
			}
			
			service.setGuaranteeStatus(entity.getApprResult());
			servicePersistService.update(service);
			
		}
	}
}