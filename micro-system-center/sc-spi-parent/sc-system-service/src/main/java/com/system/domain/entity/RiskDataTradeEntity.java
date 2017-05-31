package com.system.domain.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by maven on 2017/3/13.
 */

@Entity
@Table(name = "risk_data_trade", schema = "public_service_db", catalog = "")
public class RiskDataTradeEntity {
    private Integer id;
    private String phone;
    private String eventType;
    private String source;
    private String ipAddress;
    private BigDecimal amount;
    private String orderId;
    private String tradeId;
    private String remark;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String systemUserId;
    private Timestamp eventTime;
    private Integer systemId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "event_type")
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Basic
    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Basic
    @Column(name = "ip_address")
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Basic
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "trade_id")
    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Basic
    @Column(name = "system_user_id")
    public String getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(String systemUserId) {
        this.systemUserId = systemUserId;
    }

    @Basic
    @Column(name = "event_time")
    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    @Basic
    @Column(name = "system_id")
    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RiskDataTradeEntity that = (RiskDataTradeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (eventType != null ? !eventType.equals(that.eventType) : that.eventType != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (ipAddress != null ? !ipAddress.equals(that.ipAddress) : that.ipAddress != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (tradeId != null ? !tradeId.equals(that.tradeId) : that.tradeId != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (systemUserId != null ? !systemUserId.equals(that.systemUserId) : that.systemUserId != null) return false;
        if (eventTime != null ? !eventTime.equals(that.eventTime) : that.eventTime != null) return false;
        if (systemId != null ? !systemId.equals(that.systemId) : that.systemId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (tradeId != null ? tradeId.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (systemUserId != null ? systemUserId.hashCode() : 0);
        result = 31 * result + (eventTime != null ? eventTime.hashCode() : 0);
        result = 31 * result + (systemId != null ? systemId.hashCode() : 0);
        return result;
    }
}
