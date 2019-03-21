package com.auto.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.app.base.common.AppException;
import com.app.base.utils.StringUtils;
import com.app.base.web.JsonParameter;
import com.app.base.web.SessionParameter;
import com.app.base.web.common.MessageResponse;
import com.app.base.web.common.ResponseCode;
import com.app.base.web.controller.FileDesc;
import com.app.base.web.controller.MultipartController;
import com.auto.service.IDriverServiceFacade;

@Controller
@RequestMapping("/Driver")
public class DriverController extends MultipartController 
{
	private static Logger logger = LoggerFactory.getLogger(DriverController.class);
	
	@Resource(name ="DriverserviceFacade")
	private IDriverServiceFacade serviceFacade;

	public DriverController(){}
	
	
	/**
	 * 注销请求接入
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/Logout.*")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/home.html"; 
	}
	
	/**
	 * 司机身份认证接口
	 * @param driverId
	 * @param name
	 * @param licenseNo
	 * @return
	 */
	@RequestMapping(value="/Authentication/Insert.*")
	public @ResponseBody MessageResponse driverAuthenticationApplication(@JsonParameter("driverId") String driverId,  @JsonParameter("name") String name, @JsonParameter("licenseNo") String licenseNo, @RequestParam(value="licenseFile")  MultipartFile licenseFile)
	{
		MessageResponse response = null;
		try{
			FileDesc desc = this.saveFile("Driver", "licenseFile", licenseFile);
			if(desc != null){
				serviceFacade.driverAuthentication(driverId, name, licenseNo, desc.getFinalFileName());
				response = new MessageResponse(ResponseCode.Success, "提交认证成功");
			}else{
				response = new MessageResponse(ResponseCode.LogicException, "需要上传证件照片。");
			}
			return response;
		}catch(Exception exc){
			logger.error(StringUtils.format("Save Exception for request={0}.", new Object[]{driverId,name,licenseNo}), exc);
			response = new MessageResponse(exc);
		}
		return response;
	}
	
	/**
	 * 司机身份认证接口
	 * @param driverId
	 * @param name
	 * @param licenseNo
	 * @return
	 */
	@RequestMapping(value="/authenticate/Approve.*")
	public @ResponseBody MessageResponse driverAuthenticationApprove(@JsonParameter("driverId") String driverId,  @JsonParameter("licenseAuthenticated") Integer licenseAuthenticated, @JsonParameter("licenseAuthenticatedMemo") String licenseAuthenticatedMemo)
	{
		MessageResponse response = null;
		try{
			serviceFacade.driverAuthenticationApprove(driverId, licenseAuthenticated, licenseAuthenticatedMemo);
			response = new MessageResponse(ResponseCode.Success, "认证成功");
		}catch(Exception exc){
			logger.error(StringUtils.format("Save Exception for request={0}.", new Object[]{driverId,licenseAuthenticated,licenseAuthenticatedMemo}), exc);
			response = new MessageResponse(exc);
		}
		return response;
	}
	
	/**
	 * 司机手机号绑定
	 * @param driverId
	 * @param mobileNo
	 * @param vcode
	 * @param sessionCode 缓存验证码
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/Bind/Phone.*")
	public @ResponseBody MessageResponse bindingPhone(@JsonParameter("driverId") String driverId,  @JsonParameter("mobileNo") String mobileNo, @JsonParameter("vcode")String vcode,@SessionParameter("RANDOMVALIDATECODEKEY") String sessionCode)
	{
		MessageResponse response = null;
		try
		{	
			if(sessionCode != null &&sessionCode.equals(vcode))
			{
				serviceFacade.bindingPhone(driverId, mobileNo);
				response = new MessageResponse(ResponseCode.Success, "绑定成功.");
			}
			else
			{
				response=new MessageResponse(new AppException("验证码已经失效!"));
			}
		} 
		catch (Exception exc) 
		{
			logger.error(StringUtils.format("Bind Exception for request={0}.", new Object[]{driverId,mobileNo}), exc);
			response = new MessageResponse(exc);
		}
		return response;
	}
	
	/**
	 * 手机号码验证接口
	 * @param mobileNo
	 * @param vcode  
	 * @param sessionCode  缓存的验证码
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/Phone/Validation.*")
	public @ResponseBody MessageResponse validationPhoneCode(@JsonParameter("mobileNo") String mobileNo, @JsonParameter("vcode")String vcode,@SessionParameter("RANDOMVALIDATECODEKEY") String sessionCode)
	{
		MessageResponse response = null;
		try
		{	
			if(sessionCode != null && sessionCode.equals(vcode))
			{
				response = new MessageResponse(ResponseCode.Success, "验证通过!");
			}
			else
			{
				response=new MessageResponse(new AppException("验证码已经失效！"));
			}
		} 
		catch (Exception exc) 
		{
			logger.error(StringUtils.format("Validation Exception for request={0}.", new Object[]{mobileNo}), exc);
			response = new MessageResponse(exc);
		}
		return response;
	}
}
