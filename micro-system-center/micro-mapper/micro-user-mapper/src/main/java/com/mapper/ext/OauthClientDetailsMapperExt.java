package com.mapper.ext;


import com.entity.OauthClientDetails;
import com.mapper.generate.OauthClientDetailsMapper;

public interface OauthClientDetailsMapperExt extends OauthClientDetailsMapper {


    OauthClientDetails selectByClientId(String clientId);

}