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
@Table(name = "risk_alarm_rule", schema = "public_service_db", catalog = "")
public class RiskAlarmRuleEntity {
    private Integer ruleId;
    private String ruleName;
    private String ruleDescription;
    private String ruleContent;
    private Integer systemId;
    private Byte ruleStatus;
    private String noticeEmail;
    private String noticeTel;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "rule_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    @Basic
    @Column(name = "rule_name")
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    @Basic
    @Column(name = "rule_description")
    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    @Basic
    @Column(name = "rule_content")
    public String getRuleContent() {
        return ruleContent;
    }

    public void setRuleContent(String ruleContent) {
        this.ruleContent = ruleContent;
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
    @Column(name = "rule_status")
    public Byte getRuleStatus() {
        return ruleStatus;
    }

    public void setRuleStatus(Byte ruleStatus) {
        this.ruleStatus = ruleStatus;
    }

    @Basic
    @Column(name = "notice_email")
    public String getNoticeEmail() {
        return noticeEmail;
    }

    public void setNoticeEmail(String noticeEmail) {
        this.noticeEmail = noticeEmail;
    }

    @Basic
    @Column(name = "notice_tel")
    public String getNoticeTel() {
        return noticeTel;
    }

    public void setNoticeTel(String noticeTel) {
        this.noticeTel = noticeTel;
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

        RiskAlarmRuleEntity that = (RiskAlarmRuleEntity) o;

        if (ruleId != null ? !ruleId.equals(that.ruleId) : that.ruleId != null) return false;
        if (ruleName != null ? !ruleName.equals(that.ruleName) : that.ruleName != null) return false;
        if (ruleDescription != null ? !ruleDescription.equals(that.ruleDescription) : that.ruleDescription != null)
            return false;
        if (ruleContent != null ? !ruleContent.equals(that.ruleContent) : that.ruleContent != null) return false;
        if (systemId != null ? !systemId.equals(that.systemId) : that.systemId != null) return false;
        if (ruleStatus != null ? !ruleStatus.equals(that.ruleStatus) : that.ruleStatus != null) return false;
        if (noticeEmail != null ? !noticeEmail.equals(that.noticeEmail) : that.noticeEmail != null) return false;
        if (noticeTel != null ? !noticeTel.equals(that.noticeTel) : that.noticeTel != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ruleId != null ? ruleId.hashCode() : 0;
        result = 31 * result + (ruleName != null ? ruleName.hashCode() : 0);
        result = 31 * result + (ruleDescription != null ? ruleDescription.hashCode() : 0);
        result = 31 * result + (ruleContent != null ? ruleContent.hashCode() : 0);
        result = 31 * result + (systemId != null ? systemId.hashCode() : 0);
        result = 31 * result + (ruleStatus != null ? ruleStatus.hashCode() : 0);
        result = 31 * result + (noticeEmail != null ? noticeEmail.hashCode() : 0);
        result = 31 * result + (noticeTel != null ? noticeTel.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
