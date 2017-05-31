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
 * Created by maven on 2017/3/26.
 */

@Entity
@Table(name = "oauth_system_resource", schema = "public_service_db", catalog = "")
public class OauthSystemResourceEntity {
    private Integer oauthSystemResourceId;
    private Integer systemId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer resourceId;

    @Id
    @Column(name = "oauth_system_resource_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getOauthSystemResourceId() {
        return oauthSystemResourceId;
    }

    public void setOauthSystemResourceId(Integer oauthSystemResourceId) {
        this.oauthSystemResourceId = oauthSystemResourceId;
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

    @Basic
    @Column(name = "resource_id")
    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OauthSystemResourceEntity that = (OauthSystemResourceEntity) o;

        if (oauthSystemResourceId != null ? !oauthSystemResourceId.equals(that.oauthSystemResourceId) : that.oauthSystemResourceId != null)
            return false;
        if (systemId != null ? !systemId.equals(that.systemId) : that.systemId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (resourceId != null ? !resourceId.equals(that.resourceId) : that.resourceId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oauthSystemResourceId != null ? oauthSystemResourceId.hashCode() : 0;
        result = 31 * result + (systemId != null ? systemId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (resourceId != null ? resourceId.hashCode() : 0);
        return result;
    }
}
