package com.auto.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.app.base.common.AppException;
import com.app.base.utils.StringUtils;
import com.app.base.web.common.DataFormResponse;
import com.app.base.web.common.ResponseCode;
import com.app.base.web.controller.FileDesc;
import com.app.base.web.controller.MultipartController;

@Controller
@RequestMapping("/file/")
public class FileUploadController extends MultipartController {
	@Value("#{appconfig['file.upload.location']}")
	private String uploadLocation = null;
	
	public FileUploadController() {
	}
	
	/**
	 * 新建业务对象信息
	 * @param entity
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{module}/{category}/upload.*", method=RequestMethod.POST)
	public @ResponseBody DataFormResponse<FileDesc> upload(@PathVariable("module") String module, @PathVariable("category") String category, MultipartHttpServletRequest request){
		DataFormResponse<FileDesc> response = null;
		
		try{
			MultiValueMap<String, MultipartFile> files = request.getMultiFileMap();
			if(files != null && ! files.isEmpty()){
				Set<String> fileNames = files.keySet();
				for(String fileName : fileNames){
					List<MultipartFile> lFiles = files.get(fileName);
					if(lFiles == null || lFiles.isEmpty()) continue;
					
					FileDesc fileDesc = saveFile(module, category, lFiles.get(0));
					if(fileDesc != null){
						response = new DataFormResponse<FileDesc>(ResponseCode.Success.code, fileDesc);
					}else{
						response = new DataFormResponse<FileDesc>(new AppException("需要提交附件。"));
					}
				}
			}
		}catch(Exception exc){
			logger.error(StringUtils.format("Upload Exception for request={0}.", new Object[]{module}), exc);
			response = new DataFormResponse<FileDesc>(exc);
		}
		
		return response;
	}
	
	/**
	 * 新建业务对象信息
	 * @param entity
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/image/{module}/{category}/upload.*", method=RequestMethod.POST)
	public @ResponseBody DataFormResponse<FileDesc> uploadImage(@PathVariable("module") String module, @PathVariable("category") String category, MultipartHttpServletRequest request){
		DataFormResponse<FileDesc> response = this.upload(module, category, request);
		
		if(response.getStatus_code().equals(ResponseCode.Success.code)){
			response.getData().setFinalFileName(request.getContextPath() + "/" + response.getData().getFinalFileName());
		}
		
		return response;
	}
}
