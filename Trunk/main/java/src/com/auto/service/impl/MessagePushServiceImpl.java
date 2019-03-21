package com.auto.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auto.entity.PushMessageEntity;
import com.auto.service.IMessagePushService;

@Service("MessagePushServiceImpl")
public class MessagePushServiceImpl implements IMessagePushService {
	private static Logger logger = LoggerFactory
			.getLogger(MessagePushServiceImpl.class);

	@Value("#{appconfig['sendmessage.appKey']}")
	private String appKey;

	@Value("#{appconfig['sendmessage.appMasterSecret']}")
	private String appMasterSecret;

	private String timestamp = Integer.toString((int) (System
			.currentTimeMillis() / 1000));

	protected static final HashSet<String> PAYLOAD_KEYS = new HashSet<String>(
			Arrays.asList(new String[] { "display_type" }));


	protected final JSONObject rootJson = new JSONObject();

	// This object is used for sending the post request to Umeng
	protected HttpClient client = new DefaultHttpClient();

	// The host
	protected static final String host = "http://msg.umeng.com";

	// The upload path
	protected static final String uploadPath = "/upload";

	// The post path
	protected static final String postPath = "/api/send";

	// The user agent
	protected final String USER_AGENT = "Mozilla/5.0";

	// Keys can be set in the root level
	protected static final HashSet<String> ROOT_KEYS = new HashSet<String>(
			Arrays.asList(new String[] { "appkey", "timestamp", "type",
					"device_tokens", "alias", "alias_type", "file_id",
					"filter", "production_mode", "feedback", "description",
					"thirdparty_id" }));

	// Keys can be set in the policy level
	protected static final HashSet<String> POLICY_KEYS = new HashSet<String>(
			Arrays.asList(new String[] { "start_time", "expire_time",
					"max_send_num" }));

	
	// Keys can be set in the body level
    protected static final HashSet<String> BODY_KEYS = new HashSet<String>(
				Arrays.asList(new String[] { "ticker", "title", "text",
						"builder_id", "icon", "largeIcon", "img", "play_vibrate",
						"play_lights", "play_sound", "sound", "after_open", "url",
						"activity", "custom" }));
	// Set key/value in the rootJson, for the keys can be set please see
	// ROOT_KEYS, PAYLOAD_KEYS,
	// BODY_KEYS and POLICY_KEYS.
	@Override
	public boolean setPredefinedKeyValue(String key, Object value)
			throws Exception {
		if (ROOT_KEYS.contains(key)) {
			// This key should be in the root level
			rootJson.put(key, value);
		} else if (PAYLOAD_KEYS.contains(key)) {
			// This key should be in the payload level
			JSONObject payloadJson = null;
			if (rootJson.has("payload")) {
				payloadJson = rootJson.getJSONObject("payload");
			} else {
				payloadJson = new JSONObject();
				rootJson.put("payload", payloadJson);
			}
			payloadJson.put(key, value);
		} else if (BODY_KEYS.contains(key)) {
			// This key should be in the body level
			JSONObject bodyJson = null;
			JSONObject payloadJson = null;
			// 'body' is under 'payload', so build a payload if it doesn't exist
			if (rootJson.has("payload")) {
				payloadJson = rootJson.getJSONObject("payload");
			} else {
				payloadJson = new JSONObject();
				rootJson.put("payload", payloadJson);
			}
			// Get body JSONObject, generate one if not existed
			if (payloadJson.has("body")) {
				bodyJson = payloadJson.getJSONObject("body");
			} else {
				bodyJson = new JSONObject();
				payloadJson.put("body", bodyJson);
			}
			bodyJson.put(key, value);
		} else if (POLICY_KEYS.contains(key)) {
			// This key should be in the body level
			JSONObject policyJson = null;
			if (rootJson.has("policy")) {
				policyJson = rootJson.getJSONObject("policy");
			} else {
				policyJson = new JSONObject();
				rootJson.put("policy", policyJson);
			}
			policyJson.put(key, value);
		} else {
			if (key == "payload" || key == "body" || key == "policy"
					|| key == "extra") {
				throw new Exception("You don't need to set value for " + key
						+ " , just set values for the sub keys in it.");
			} else {
				throw new Exception("Unknown key: " + key);
			}
		}
		return true;
	}

	// Set extra key/value for Android notification
	@Override
	public boolean setExtraField(String key, String value) throws Exception {
		JSONObject payloadJson = null;
		JSONObject extraJson = null;
		if (rootJson.has("payload")) {
			payloadJson = rootJson.getJSONObject("payload");
		} else {
			payloadJson = new JSONObject();
			rootJson.put("payload", payloadJson);
		}

		if (payloadJson.has("extra")) {
			extraJson = payloadJson.getJSONObject("extra");
		} else {
			extraJson = new JSONObject();
			payloadJson.put("extra", extraJson);
		}
		extraJson.put(key, value);
		return true;
	}

	@Override
	public void send(PushMessageEntity message) throws Exception {
		// TODO Auto-generated method stub
		String title = message.getTitle();
		String content = message.getContent();
		List<Map<String, String>> devnolist = message.getDevNoList();
		try {
			this.setPredefinedKeyValue("type", "listcast");
			this.setPredefinedKeyValue("appkey", appKey);
			this.setPredefinedKeyValue("title", title);
			this.setPredefinedKeyValue("timestamp", timestamp);
			this.setPredefinedKeyValue("ticker", "你有未读消息！");
			this.setPredefinedKeyValue("text", content);
			this.setPredefinedKeyValue("after_open", "go_custom");
			this.setPredefinedKeyValue("production_mode", "true");
			this.setPredefinedKeyValue("display_type", "notification");
			int size = devnolist.size() - 1;
			StringBuffer buf = new StringBuffer();
			while (size > 0) {
				Map<String, String> map = devnolist.get(size);
				String key = null;
				String value = null;
				if (map.keySet().iterator().hasNext()) {
					key = map.keySet().iterator().next().toString();
				}
				value = map.get(key);
				if (value.equals("1")) {
					buf.append(key + ",");
				}
				size--;
			}

			Map<String, String> map = devnolist.get(size);
			String key = null;
			String value = null;
			if (map.keySet().iterator().hasNext()) {
				key = map.keySet().iterator().next().toString();
			}
			value = map.get(key);
			if (value.equals("1")) {
				buf.append(key);
			}
			this.setPredefinedKeyValue("device_tokens", buf);
			this.send();
		} catch (Exception e) {
			logger.error("SendMessage Exception.", e);
		}
	}
	@Override
	public int send() throws Exception {
		String url = host + postPath;
		String postBody = rootJson.toString();
		String sign = DigestUtils
				.md5Hex(("POST" + url + postBody + appMasterSecret)
						.getBytes("utf8"));
		url = url + "?sign=" + sign;
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", USER_AGENT);
		StringEntity se = new StringEntity(postBody, "UTF-8");
		post.setEntity(se);
		// Send the post request and get the response
		HttpResponse response = client.execute(post);
		int status = response.getStatusLine().getStatusCode();
		System.out.println("Response Code : " + status);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result.toString());
		if (status == 200) {
			System.out.println("Notification sent successfully.");
		} else {
			System.out.println("Failed to send the notification!");
		}
		return status;
	}
	
	
	public String getTimestamp() {
		return timestamp;
	}
    
	@Override
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}