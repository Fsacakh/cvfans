package com.auto.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.base.utils.StringUtils;
import com.app.base.web.JsonParameter;
import com.app.base.web.common.DataFormResponse;
import com.app.base.web.common.ResponseCode;
import com.app.base.web.controller.BaseController;
import com.auto.entity.DeviceVersionEntity;
import com.auto.service.IDeviceServiceFacade;

@Controller
@RequestMapping("/device/")
public class DeviceController extends BaseController {
	@Resource(name="DeviceServiceFacadeImpl")
	private IDeviceServiceFacade<DeviceVersionEntity> serviceFacade = null;
	
	@Value("#{appconfig['version.upgrade.message.1']}")
	private String optionMessage = null;
	
	@Value("#{appconfig['version.upgrade.message.2']}")
	private String requiredMessage = null;
	
	
	
	public DeviceController() {
	}
	
	@RequestMapping(value="/version/Update.*")
	public @ResponseBody DataFormResponse<DeviceVersionEntity> getUpgradeVersionInfo(@JsonParameter("deviceType") String deviceType, @JsonParameter("versionNo") String versionNo, @RequestHeader(value="userId", required=false) String userId,  @RequestHeader(value="uuid", required=false) String uuid){
		logger.error("userId=" + userId + ",devType=" + deviceType);
		
		
		DataFormResponse<DeviceVersionEntity> response = null;
		try{
			DeviceVersionEntity currentVersion = new DeviceVersionEntity();
			currentVersion.setDevType(deviceType);
			
			DeviceVersionEntity version = this.serviceFacade.getLatestVersionInfo(currentVersion, userId, uuid, deviceType);
			if(version != null){
				int flag = 0;
				String sourceVersion = formatVersion(versionNo);
				logger.warn("source=" + sourceVersion + ", target=" + formatVersion(version.getVersionNo()));
				if (sourceVersion.compareTo(formatVersion(version.getVersionNo())) < 0) {
					if (compare(sourceVersion, version.getRequiredVersionNo())) {
						flag = 2;
					} else {
						flag = 1;
					}
				}

				version.setRequired(flag);
				if (flag > 0) {
					String versionMessage = null;
					if(flag == 1){
						versionMessage = "现有新版本需要更新,是否需要现在升级?";
					}else if(flag == 2){
						versionMessage = "现有新版本需要强制升级,点击确定进行升级。";
					}
					
					version.setMessage(versionMessage);
				}
			}else{
				version = new DeviceVersionEntity();
			}
			
			response = new DataFormResponse<DeviceVersionEntity>(ResponseCode.Success.code,version);
		}catch(Exception exc){
			logger.error(StringUtils.format("Version check Exception for deviceType=[{0}], versionNo=[{1}].", new Object[]{deviceType, versionNo}), exc);
			response = new DataFormResponse<DeviceVersionEntity>(exc);
		}
		
		return response;
	}
	
	/**
	 * 
	 * @param source
	 *            客户端上传版本号
	 * @param target
	 *            目标版本号段
	 * @return
	 */
	private boolean compare(String source, String upgradeSection) {
		try {
			if (upgradeSection == null)
				return false;

			String[] sections = upgradeSection.split("-");
			String from = formatVersion(sections[0]);
			String end = null;
			if (sections.length == 1) {
				end = from;
			} else {
				end = formatVersion(sections[1]);
			}

			return source.compareTo(from) >= 0 && source.compareTo(end) <= 0;
		} catch (Exception exc) {
			logger.error("Exception message.", exc);
			return false;
		}
	}

	/**
	 * 格式化版本格式为 XXXXXX
	 * 
	 * @param versionNo
	 * @return
	 * @throws Exception
	 */
	private String formatVersion(String versionNo) throws Exception {
		StringBuffer sb = new StringBuffer();

		String[] versions = versionNo.split("[.]");

		int length = versions.length;
		for (int index = 0; index < length; index++) {
			if (versions[index].length() < 2) {
				sb.append("0");
			}
			sb.append(versions[index]);
		}
		
		return sb.toString();
	}

}
