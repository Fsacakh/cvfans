package com.auto.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.base.utils.DateUtils;
import com.app.base.utils.KeyUtils;
import com.app.components.persist.PersistServiceFacadeImpl;
import com.auto.entity.FaultEntity;

@Service("FaultServiceFacade")
public class FaultServiceFacade extends PersistServiceFacadeImpl<FaultEntity> {
	private static Logger logger = LoggerFactory.getLogger(FaultServiceFacade.class);
	
	@Value("#{appconfig['artile.location']}")
	private String location = null;
	
	@Override
	public void insert(FaultEntity entity) throws Exception {
		saveHtml(entity);
		super.insert(entity);
	}

	@Override
	public void update(FaultEntity entity) throws Exception {
		saveHtml(entity);
		super.update(entity);
	}
	
	private void saveHtml(FaultEntity entity) throws Exception{
		String content = entity.getContent();
		BufferedWriter bw = null;
		try{
			String fullFile = null;
			if(entity.getUrl() != null){
				fullFile = location + "/" + entity.getUrl();
			}else{
				String date = DateUtils.formatDate(new Date(), "yyyyMMdd");
				String relativeDir = "/fault/" + date;
				String fullPath = location + relativeDir;
				String fileName = KeyUtils.generate() + ".htm";
				File dir = new File(fullPath);
				if(! dir.exists()){
					dir.mkdirs();
				}
				
				fullFile = fullPath + "/" + fileName;
				entity.setUrl(relativeDir + "/" + fileName);
			}
			
			bw = new BufferedWriter(new FileWriter(fullFile));
			bw.write(content);
		}catch(Exception exc){
			logger.error("Save Html Exception", exc);
		}finally{
			if(bw != null){
				bw.flush();
				bw.close();
			}
		}
		
	}
}
