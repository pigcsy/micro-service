package com.system.domain.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by maven on 2017/3/30.
 */

@Entity
@Table(name = "oauth_system", schema = "public_service_db", catalog = "")
public class OauthSystemEntity {
    private Integer systemId;
    private String systemName;
    private String systemRemark;
    private String clientId;
    private Integer systemType;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer dailyAccessNum;
    private Integer minuteAccessNum;

    @Id
    @Column(name = "system_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    @Basic
    @Column(name = "system_name")
    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @Basic
    @Column(name = "system_remark")
    public String getSystemRemark() {
        return systemRemark;
    }

    public void setSystemRemark(String systemRemark) {
        this.systemRemark = systemRemark;
    }

    @Basic
    @Column(name = "client_id")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "system_type")
    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OauthSystemEntity that = (OauthSystemEntity) o;

        if (systemId != null ? !systemId.equals(that.systemId) : that.systemId != null) return false;
        if (systemName != null ? !systemName.equals(that.systemName) : that.systemName != null) return false;
        if (systemRemark != null ? !systemRemark.equals(that.systemRemark) : that.systemRemark != null) return false;
        if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;
        if (systemType != null ? !systemType.equals(that.systemType) : that.systemType != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = systemId != null ? systemId.hashCode() : 0;
        result = 31 * result + (systemName != null ? systemName.hashCode() : 0);
        result = 31 * result + (systemRemark != null ? systemRemark.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (systemType != null ? systemType.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "daily_access_num")
    public Integer getDailyAccessNum() {
        return dailyAccessNum;
    }

    public void setDailyAccessNum(Integer dailyAccessNum) {
        this.dailyAccessNum = dailyAccessNum;
    }

    @Basic
    @Column(name = "minute_access_num")
    public Integer getMinuteAccessNum() {
        return minuteAccessNum;
    }

    public void setMinuteAccessNum(Integer minuteAccessNum) {
        this.minuteAccessNum = minuteAccessNum;
    }
}
