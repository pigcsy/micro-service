package com.system.domain.entity;

import org.hibernate.annotations.Type;

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
@Table(name = "oauth_client_details", schema = "public_service_db", catalog = "")
public class OauthClientDetailsEntity {
    private String clientId;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Double accessTokenValidity;
    private Double refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer id;

    @Basic
    @Column(name = "client_id")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "resource_ids")
    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    @Basic
    @Column(name = "client_secret")
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Basic
    @Column(name = "scope")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "authorized_grant_types")
    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    @Basic
    @Column(name = "web_server_redirect_uri")
    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    @Basic
    @Column(name = "authorities")
    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Basic
    @Column(name = "access_token_validity")
    public Double getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Double accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    @Basic
    @Column(name = "refresh_token_validity")
    public Double getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Double refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    @Basic
    @Column(name = "additional_information")
    @Type(type = "text")
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Basic
    @Column(name = "autoapprove")
    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
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

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OauthClientDetailsEntity that = (OauthClientDetailsEntity) o;

        if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;
        if (resourceIds != null ? !resourceIds.equals(that.resourceIds) : that.resourceIds != null) return false;
        if (clientSecret != null ? !clientSecret.equals(that.clientSecret) : that.clientSecret != null) return false;
        if (scope != null ? !scope.equals(that.scope) : that.scope != null) return false;
        if (authorizedGrantTypes != null ? !authorizedGrantTypes.equals(that.authorizedGrantTypes) : that.authorizedGrantTypes != null)
            return false;
        if (webServerRedirectUri != null ? !webServerRedirectUri.equals(that.webServerRedirectUri) : that.webServerRedirectUri != null)
            return false;
        if (authorities != null ? !authorities.equals(that.authorities) : that.authorities != null) return false;
        if (accessTokenValidity != null ? !accessTokenValidity.equals(that.accessTokenValidity) : that.accessTokenValidity != null)
            return false;
        if (refreshTokenValidity != null ? !refreshTokenValidity.equals(that.refreshTokenValidity) : that.refreshTokenValidity != null)
            return false;
        if (additionalInformation != null ? !additionalInformation.equals(that.additionalInformation) : that.additionalInformation != null)
            return false;
        if (autoapprove != null ? !autoapprove.equals(that.autoapprove) : that.autoapprove != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clientId != null ? clientId.hashCode() : 0;
        result = 31 * result + (resourceIds != null ? resourceIds.hashCode() : 0);
        result = 31 * result + (clientSecret != null ? clientSecret.hashCode() : 0);
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        result = 31 * result + (authorizedGrantTypes != null ? authorizedGrantTypes.hashCode() : 0);
        result = 31 * result + (webServerRedirectUri != null ? webServerRedirectUri.hashCode() : 0);
        result = 31 * result + (authorities != null ? authorities.hashCode() : 0);
        result = 31 * result + (accessTokenValidity != null ? accessTokenValidity.hashCode() : 0);
        result = 31 * result + (refreshTokenValidity != null ? refreshTokenValidity.hashCode() : 0);
        result = 31 * result + (additionalInformation != null ? additionalInformation.hashCode() : 0);
        result = 31 * result + (autoapprove != null ? autoapprove.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

}
