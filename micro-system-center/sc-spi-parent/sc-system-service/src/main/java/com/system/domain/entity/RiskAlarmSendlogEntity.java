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
@Table(name = "risk_alarm_sendlog", schema = "public_service_db", catalog = "")
public class RiskAlarmSendlogEntity {
    private Integer riskAlarmSendId;
    private Timestamp sendTime;
    private String sendTo;
    private Byte sendType;
    private String sendMsg;
    private Byte sendStatus;
    private String sendRemark;
    private Integer systemId;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "risk_alarm_send_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getRiskAlarmSendId() {
        return riskAlarmSendId;
    }

    public void setRiskAlarmSendId(Integer riskAlarmSendId) {
        this.riskAlarmSendId = riskAlarmSendId;
    }

    @Basic
    @Column(name = "send_time")
    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "send_to")
    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    @Basic
    @Column(name = "send_type")
    public Byte getSendType() {
        return sendType;
    }

    public void setSendType(Byte sendType) {
        this.sendType = sendType;
    }

    @Basic
    @Column(name = "send_msg")
    public String getSendMsg() {
        return sendMsg;
    }

    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }

    @Basic
    @Column(name = "send_status")
    public Byte getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Byte sendStatus) {
        this.sendStatus = sendStatus;
    }

    @Basic
    @Column(name = "send_remark")
    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
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

        RiskAlarmSendlogEntity that = (RiskAlarmSendlogEntity) o;

        if (riskAlarmSendId != null ? !riskAlarmSendId.equals(that.riskAlarmSendId) : that.riskAlarmSendId != null)
            return false;
        if (sendTime != null ? !sendTime.equals(that.sendTime) : that.sendTime != null) return false;
        if (sendTo != null ? !sendTo.equals(that.sendTo) : that.sendTo != null) return false;
        if (sendType != null ? !sendType.equals(that.sendType) : that.sendType != null) return false;
        if (sendMsg != null ? !sendMsg.equals(that.sendMsg) : that.sendMsg != null) return false;
        if (sendStatus != null ? !sendStatus.equals(that.sendStatus) : that.sendStatus != null) return false;
        if (sendRemark != null ? !sendRemark.equals(that.sendRemark) : that.sendRemark != null) return false;
        if (systemId != null ? !systemId.equals(that.systemId) : that.systemId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = riskAlarmSendId != null ? riskAlarmSendId.hashCode() : 0;
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (sendTo != null ? sendTo.hashCode() : 0);
        result = 31 * result + (sendType != null ? sendType.hashCode() : 0);
        result = 31 * result + (sendMsg != null ? sendMsg.hashCode() : 0);
        result = 31 * result + (sendStatus != null ? sendStatus.hashCode() : 0);
        result = 31 * result + (sendRemark != null ? sendRemark.hashCode() : 0);
        result = 31 * result + (systemId != null ? systemId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
