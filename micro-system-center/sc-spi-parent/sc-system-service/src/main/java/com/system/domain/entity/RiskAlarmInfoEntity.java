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
 * Created by maven on 2017/3/13.
 */

@Entity
@Table(name = "risk_alarm_info", schema = "public_service_db", catalog = "")
public class RiskAlarmInfoEntity {
    private Integer alarmId;
    private Byte alarmType;
    private String alarmMsg;
    private Integer systemId;
    private String systemUserId;
    private String systemUserTel;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "alarm_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    @Basic
    @Column(name = "alarm_type")
    public Byte getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Byte alarmType) {
        this.alarmType = alarmType;
    }

    @Basic
    @Column(name = "alarm_msg")
    public String getAlarmMsg() {
        return alarmMsg;
    }

    public void setAlarmMsg(String alarmMsg) {
        this.alarmMsg = alarmMsg;
    }

    @Basic
    @Column(name = "system_id")
    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    @Basic
    @Column(name = "system_user_id")
    public String getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(String systemUserId) {
        this.systemUserId = systemUserId;
    }

    @Basic
    @Column(name = "system_user_tel")
    public String getSystemUserTel() {
        return systemUserTel;
    }

    public void setSystemUserTel(String systemUserTel) {
        this.systemUserTel = systemUserTel;
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

        RiskAlarmInfoEntity that = (RiskAlarmInfoEntity) o;

        if (alarmId != null ? !alarmId.equals(that.alarmId) : that.alarmId != null) return false;
        if (alarmType != null ? !alarmType.equals(that.alarmType) : that.alarmType != null) return false;
        if (alarmMsg != null ? !alarmMsg.equals(that.alarmMsg) : that.alarmMsg != null) return false;
        if (systemId != null ? !systemId.equals(that.systemId) : that.systemId != null) return false;
        if (systemUserId != null ? !systemUserId.equals(that.systemUserId) : that.systemUserId != null) return false;
        if (systemUserTel != null ? !systemUserTel.equals(that.systemUserTel) : that.systemUserTel != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = alarmId != null ? alarmId.hashCode() : 0;
        result = 31 * result + (alarmType != null ? alarmType.hashCode() : 0);
        result = 31 * result + (alarmMsg != null ? alarmMsg.hashCode() : 0);
        result = 31 * result + (systemId != null ? systemId.hashCode() : 0);
        result = 31 * result + (systemUserId != null ? systemUserId.hashCode() : 0);
        result = 31 * result + (systemUserTel != null ? systemUserTel.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
