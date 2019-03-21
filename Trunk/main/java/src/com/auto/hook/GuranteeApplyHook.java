package com.auto.hook;


import com.app.base.sao.rdbms.Persist;
import com.app.base.utils.DateUtils;
import com.auto.common.impl.DefaultPersistHook;
import com.auto.entity.AutoGuaranteeEntity;

public class GuranteeApplyHook extends DefaultPersistHook<AutoGuaranteeEntity> {

	@Override
	public void postPersist(AutoGuaranteeEntity arg0, Persist arg1)
			throws Exception {
		
		
	}

	@Override
	public void prePersist(AutoGuaranteeEntity autoGuaranteeEntity, Persist arg1)
			throws Exception {
		switch (arg1) {
		case Insert:
			autoGuaranteeEntity.setApplDate(DateUtils.getCurrentDate());
			break;
		case Update:	
			autoGuaranteeEntity.setApprDate(DateUtils.getCurrentDate());
		default:
			break;
		}
	}

}
