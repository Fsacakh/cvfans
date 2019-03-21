package com.auto.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.base.sao.rdbms.JdbcPersister;
import com.app.base.utils.CodeUtils;
import com.app.base.web.JsonParameter;
import com.app.base.web.common.DataFormResponse;
import com.app.base.web.common.ResponseCode;
import com.app.base.web.controller.BaseController;
import com.auto.entity.UserEntity;
import com.auto.service.IMessageServiceFacade;


@Controller
@RequestMapping("/Code/")
public class VerifyCodeController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(JdbcPersister.class);
	
	@Resource(name = "MessageServiceFacadeImpl")
	private IMessageServiceFacade msgServiceFacade;
	
	@Value("#{appconfig['msg.successStateCode']}")
	private String  successStateCode= null;
	
	public VerifyCodeController() {
	}
	
	/**
	 * 验证码发送接入
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/Send.*")
	public @ResponseBody DataFormResponse<UserEntity> send(HttpServletRequest request, @JsonParameter("mobileNo") String mobileNo)
	throws Exception{
		DataFormResponse<UserEntity> message=null;
		
		try{
			HttpSession session = request.getSession();
	    	String randomString = CodeUtils.getRandomString(4);
	    	session.setAttribute(CodeUtils.RANDOMCODEKEY, randomString);
	    	
	    	UserEntity user =msgServiceFacade.sendCode(mobileNo, randomString);
			
	    	message=new DataFormResponse<UserEntity>(ResponseCode.Success.code, user);
		}catch(Exception exc){
			logger.error("Register Exception.", exc);
			message = new DataFormResponse<UserEntity>(exc);
		}
		
		return message;
	}
	
	/**
	 * 获取验证码图片接入
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value="/Get.*")
	public void get(HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        try {
        	HttpSession session = request.getSession();
        	String randomString = CodeUtils.getRandomString(4);
        	session.setAttribute(CodeUtils.RANDOMCODEKEY, randomString);
        	
        	CodeUtils.output(response, randomString);
        } catch (Exception e) {
            logger.error("Exception Message.", e);
        }
	}
}
