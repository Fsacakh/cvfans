package com.auto.mocker;

import java.io.File;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.app.base.utils.BeanUtils;
import com.auto.entity.DriverEntity;
import com.auto.entity.UserEntity;

public class RemoteDebug {
	// 浏览器Agent
		public static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.151 Safari/535.19";
		
		// 创建并配置HttpClient
		private static final CloseableHttpClient httpClient = HttpClients
				.custom()
				.setUserAgent(USER_AGENT)
				.setDefaultRequestConfig(
						RequestConfig.custom()
								.setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
								.build()).build();
		
	public static void main(String[] args) throws Exception{
		HttpPost post = new HttpPost("http://localhost:8080/auto/MultipartPersist/User/Insert.action");
		 
		try{
	        UserEntity user1 = new UserEntity();
	        user1.setUserId("1111");
	        user1.setUserName("Tony");
	        DriverEntity driver = new DriverEntity();
			driver.setAddress("adfafadsfadsfdsa");
			user1.setOwner(driver);
			
			FileBody fb = new FileBody(new File("E:/tmp/T_AUTO_ATTACHMENTS.sql"));  ;
			StringBody s = new StringBody(BeanUtils.writeValueAsString(user1));
	
			MultipartEntity reqEntity = new MultipartEntity();  
			reqEntity.addPart("file1", fb);
			reqEntity.addPart("User", s);
	       
			post.setEntity(reqEntity);
			
			HttpResponse res = httpClient.execute(post);
	        System.out.println(res.getStatusLine().getStatusCode());
		}finally {
			  if(post != null) { //不要忘记释放，尽量通过该方法实现，
				  post.releaseConnection();
			  }
		}
	}
	
}
