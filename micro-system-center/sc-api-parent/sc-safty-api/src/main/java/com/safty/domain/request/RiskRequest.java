package com.safty.domain.request;

import java.io.Serializable;
import java.util.Map;

/**
 * @author 金麒麟
 *         交易数据请求
 */
public class RiskRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6784261776093768134L;

    private String phone;

    private String userId;

    private String eventType;

    private String eventTime;

    private String source;

    private String ipAddress;

    private Map<String, String> eventDescription;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Map<String, String> getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(Map<String, String> eventDescription) {
        this.eventDescription = eventDescription;
    }


}
