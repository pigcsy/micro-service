package com.eureka.provider;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import de.codecentric.boot.admin.config.NotifierConfiguration.CompositeNotifierConfiguration;
import de.codecentric.boot.admin.config.NotifierConfiguration.NotifierListenerConfiguration;
import de.codecentric.boot.admin.event.ClientApplicationEvent;
import de.codecentric.boot.admin.event.ClientApplicationStatusChangedEvent;
import de.codecentric.boot.admin.notify.AbstractStatusChangeNotifier;

/**
 * Created by maven on 2017/4/13.
 */
@Configuration
@AutoConfigureBefore({ NotifierListenerConfiguration.class, CompositeNotifierConfiguration.class })
@ConfigurationProperties("spring.boot.admin.notify.dingding")
@ConditionalOnProperty(prefix = "spring.boot.admin.notify.dingding", name="enabled" ,  matchIfMissing = true)
@Profile("product")
public class DingNotice extends AbstractStatusChangeNotifier {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private String url;
	private RestTemplate restTemplate = new RestTemplate();

	public DingNotice() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void doNotify(ClientApplicationEvent event) throws Exception {
		ResponseEntity response = restTemplate.postForEntity(url, createMessage(event), Void.class);
		logger.info("响应状态:{}", response.getStatusCode());
	}

	private Map<String, Object> createMessage(ClientApplicationEvent event) {
		Map<String, Object> notifyMap = new HashMap<>();
		notifyMap.put("msgtype", "text");
		StringBuffer msgSb = new StringBuffer();
		msgSb.append("应用：").append(event.getApplication().getName()).append("\r");
		msgSb.append("URL：").append(event.getApplication().getServiceUrl()).append("\r");
		if (event instanceof ClientApplicationStatusChangedEvent) {
			ClientApplicationStatusChangedEvent changeEvent = ((ClientApplicationStatusChangedEvent) event);
			msgSb.append("原状态：").append(changeEvent.getFrom().getStatus()).append("\r");
			msgSb.append("现状态： ").append(changeEvent.getTo().getStatus());
		}

		Map<String, Object> msgMap = new HashMap<>();
		msgMap.put("content", msgSb.toString());
		notifyMap.put("text", msgMap);
		notifyMap.put("isAtAll", true);
		return notifyMap;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
