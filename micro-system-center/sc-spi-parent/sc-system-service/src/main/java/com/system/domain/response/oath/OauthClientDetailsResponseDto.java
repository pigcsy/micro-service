package com.system.domain.response.oath;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by maven on 2017/3/13.
 */
@Data
public class OauthClientDetailsResponseDto implements Serializable {
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
}
