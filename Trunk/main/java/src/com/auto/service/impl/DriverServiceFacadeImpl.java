package com.auto.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.base.common.AppException;
import com.app.components.persist.IPersistService;
import com.auto.entity.DriverEntity;
import com.auto.entity.ScoreEntity;
import com.auto.service.IDriverServiceFacade;

@Service("DriverserviceFacade")
public class DriverServiceFacadeImpl  implements IDriverServiceFacade {
	private static Logger logger = LoggerFactory.getLogger(DriverServiceFacadeImpl.class);

	@Resource(name="PersistService")
	private IPersistService<DriverEntity> driverpersistService;
	
	@Resource(name="PersistService")
	private IPersistService<ScoreEntity> scorePersistService;
	
	/**
	 * 司机身份认证接口
	 * @param driverId
	 * @param name
	 * @param licenseNo
	 * @param licenseFile
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = { Exception.class }, timeout = -1)
	public void driverAuthentication(String driverId, String name,
			String licenseNo, String licenseFileDirName) throws Exception {
		DriverEntity driver=new DriverEntity(driverId);
		driver=this.driverpersistService.load(driver);
		if(driver == null){
			logger.warn("No driver info found for driverId=" + driverId);
			throw new AppException("找不到对应的司机信息，不能进行驾照验证。");
		}
		driver.setName(name);
		driver.setLicenseNo(licenseNo);
		driver.setLicenseFile(licenseFileDirName);
		driver.setLicenseAuthenticated(2);
		driver.setLicenseAuthenticatedMemo("");
		driver.setMemberScore(driver.getMemberScore() + 200);
		this.driverpersistService.update(driver);
	}

	/**
	 * 司机手机号(更改手机号)绑定接口
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = { Exception.class }, timeout = -1)
	public void bindingPhone(String driverId, String mobileNo) throws Exception
	{
		DriverEntity driver=new DriverEntity(driverId);
		driver=this.driverpersistService.load(driver);
		if(driver==null)
		{
			logger.warn("No driver info found for driverId=" + driverId);
			throw new AppException("找不到对应的司机信息 不能进行手机号绑定。");
		}
		driver.setMobileNo(mobileNo);
		driver.setMobileBinded(1);//设置绑定收集表示符
		this.driverpersistService.update(driver);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = { Exception.class }, timeout = -1)
	public void driverAuthenticationApprove(String driverId, int licenseAuthenticated, String memo) throws Exception {
		DriverEntity driver=new DriverEntity(driverId);
		driver=this.driverpersistService.load(driver);
		if(driver == null){
			logger.warn("No driver info found for driverId=" + driverId);
			throw new AppException("找不到对应的司机信息，不能进行驾照验证。");
		}
		driver.setLicenseAuthenticated(licenseAuthenticated);
		driver.setLicenseAuthenticatedMemo(memo);
		
		driver.setMemberScore(driver.getMemberScore() + 200);
		this.driverpersistService.update(driver);
		
		if(licenseAuthenticated == 1){
			ScoreEntity score = new ScoreEntity();
			score.setComment("驾驶证认证");
			score.setOwnerId(driver.getDriverId());
			score.setScore(200);
			score.setOwnerType(1);
			scorePersistService.insert(score);
		}
	}
}
