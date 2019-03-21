package com.auto.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.base.common.AppException;
import com.app.base.utils.KeyUtils;
import com.app.base.utils.SecurityUtils;
import com.app.base.utils.StringUtils;
import com.app.components.persist.IPersistService;
import com.auto.common.Variable;
import com.auto.entity.AdminEntity;
import com.auto.entity.DriverEntity;
import com.auto.entity.EnterpriseEntity;
import com.auto.entity.ScoreEntity;
import com.auto.entity.StationEntity;
import com.auto.entity.UserEntity;
import com.auto.service.IUserServiceFacade;

@Service("UserServiceFacade")
public class UserServiceFacadeImpl implements IUserServiceFacade {
	@Resource(name="PersistService")
	private IPersistService<UserEntity> persistService;
	
	@Resource(name="PersistService")
	private IPersistService<DriverEntity> driverPersistService;
	
	@Resource(name="PersistService")
	private IPersistService<ScoreEntity> scorePersistService;
	
	@Resource(name="PersistService")
	private IPersistService<EnterpriseEntity> enterprisePersistService;
	
	@Resource(name="PersistService")
	private IPersistService<StationEntity> stationPersistService;

	public UserServiceFacadeImpl() {}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = { Exception.class }, timeout = -1)
	public void updatePassword(String userId, String oldPwd, String newPwd)
	throws Exception {
		UserEntity user = new UserEntity(userId);
		user = this.persistService.load(user);
		if(user == null){
			throw new AppException("无效用户。");
		}
		
		String password = SecurityUtils.encrypt(oldPwd);
		if(!user.getPassword().equals(password)){
			throw new AppException("旧密码不正确，请重新输入。");
		}
		
		if(oldPwd.equals(newPwd)){
			throw new AppException("新旧密码不能相同，请重新输入。");
		}
		
		user.setPassword(SecurityUtils.encrypt(newPwd));
		
		this.persistService.update(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = { Exception.class }, timeout = -1)
	public void resetPassword(String userId,String password) throws Exception {
		UserEntity user = new UserEntity(userId);
		
		user=this.persistService.load(user);
		if(user==null)
		{
			throw new AppException("此账号不存在");
		}
		
		user.setLoginCount(0);
		user.setPassword(SecurityUtils.encrypt(password));
		
		this.persistService.update(user);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = {Exception.class }, noRollbackFor={AppException.class}, timeout = -1)
	public UserEntity login(String userName, String password) throws Exception {
		UserEntity user = new UserEntity();
		user.setUserName(userName);
		user = persistService.load(user);
		if(user == null){
			throw new AppException("登录账号错误，请重新输入。");
		}
		
		String encryptedPassword = SecurityUtils.encrypt(password);
		if(! encryptedPassword.equals(user.getPassword())){
			user.setLoginCount(user.getLoginCount() + 1);
			persistService.update(user);
			if(user.getLoginCount() == 3){
				throw new AppException("登录账号或者密码连续输入错误3次被锁定，30分钟后自动解锁或者联系系统管理员进行手动解锁");
			}else{
				throw new AppException("登录账号或者密码错误，" + (3 - user.getLoginCount()) + "次登录机会");
			}
		}
		
		if(user.getUserStatus() == 0){
			throw new AppException("账号被锁定，请稍后再试");
		}
		
		
		user.setLoginCount(0);
		persistService.update(user);
		
		switch(user.getOwnerRole()){
			case 1:
				user.setOwner(this.driverPersistService.load(new DriverEntity(user.getOwnerId())));
				break;
			case 2:
				user.setOwner(this.stationPersistService.load(new StationEntity(user.getOwnerId())));
				break;
			case 3:
				user.setOwner(this.enterprisePersistService.load(new EnterpriseEntity(user.getOwnerId())));
				break;
			case 4:
				user.setOwner(new AdminEntity());
		}
		
		if(user.getOwner() == null){
			throw new AppException("账号已经被删除，不能被使用");
		}
		
		return user;
	}

	@Override
	public UserEntity register(String userId,String mobileNo, String userName, String password, int role, String introMobileNo, String nickName)
	throws Exception {
		UserEntity user = new UserEntity();
		user.setUserName(userName);
		user = this.persistService.load(user);
		if(user != null){
			throw new AppException("账号【" + userName + "】已经被注册，请换个新账号注册");
		}
		
		if(userId == null) userId = KeyUtils.generate();
		user = new UserEntity();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(SecurityUtils.encrypt(password));
		user.setOwnerRole(role);
		user.setOwnerId(userId);
		
		user.setUserStatus(Variable.Boolean.TRUE.code);
		
		if(role == Variable.Role.Driver.code){
			DriverEntity driver = new DriverEntity();
			driver.setMobileNo(mobileNo);
			driver = driverPersistService.load(driver);
			if(driver != null){
				throw new AppException("手机号已经被注册，不能再注册");
			}
			
			driver = new DriverEntity(userId);
			driver.setMobileNo(mobileNo);
			driver.setNickName(nickName);
			if(mobileNo!=null&&!mobileNo.equals(""))
			{
				driver.setMobileBinded(1);
			}
			user.setOwner(driver);
			driver.setMemberScore(100);
			this.driverPersistService.insert(driver);
			
			//积分处理
			ScoreEntity score = new ScoreEntity();
			score.setComment("用户注册");
			score.setOwnerId(driver.getDriverId());
			score.setScore(100);
			score.setOwnerType(1);
			scorePersistService.insert(score);
			
			if(StringUtils.isNotNullOrEmpty(introMobileNo)){
				DriverEntity introDriver = new DriverEntity();
				introDriver.setMobileNo(introMobileNo);
				introDriver = driverPersistService.load(introDriver);
				if(introDriver != null){
					introDriver.setMemberScore(introDriver.getMemberScore() + 200);
					driverPersistService.update(introDriver);
					
					//积分处理
					ScoreEntity introScore = new ScoreEntity();
					introScore.setComment("用户介绍");
					introScore.setOwnerId(introDriver.getDriverId());
					introScore.setScore(200);
					introScore.setOwnerType(1);
					scorePersistService.insert(introScore);
				}
			}
		}
		
		this.persistService.insert(user);
		if(role == Variable.Role.Station.code){
			StationEntity station = this.stationPersistService.load(new StationEntity(userId));
			station.setUserStatus(1);
			station.setIsMember(1); //加盟服务站
			this.stationPersistService.update(station);
		}
		
		if(role == Variable.Role.Enterprise.code){
			EnterpriseEntity enterprise = this.enterprisePersistService.load(new EnterpriseEntity(userId));
			enterprise.setUserStatus(1);
			this.enterprisePersistService.update(enterprise);
		}
		
		return user;
	}
}
