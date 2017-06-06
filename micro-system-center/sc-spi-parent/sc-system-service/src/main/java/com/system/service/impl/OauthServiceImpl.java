package com.system.service.impl;

import com.cache.support.vo.redis.resource.MetaDataSource;
import com.core.base.BaseService;
import com.core.exception.LogicException;
import com.entity.OauthClientDetails;
import com.entity.UserRoleRelation;
import com.mapper.ext.OauthClientDetailsMapperExt;
import com.mapper.ext.UserRoleRelationMapperExt;
import com.system.domain.response.oath.OauthSystemResponseDto;
import com.system.service.OauthService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ${DESCRIPTION}
 *
 * @author maven
 * @date 2017/2/23 上午9:50
 * <pre>
 *                     _oo0oo_
 *                    o8888888o
 *                    88" . "88
 *                    (| -_- |)
 *                    0\  =  /0
 *                  ___/`---'\___
 *                .' \\|     |// '.
 *               / \\|||  :  |||// \
 *              / _||||| -:- |||||- \
 *             |   | \\\  -  /// |   |
 *             | \_|  ''\---/''  |_/ |
 *             \  .-\__  '-'  ___/-. /
 *           ___'. .'  /--.--\  `. .'___
 *        ."" '<  `.___\_<|>_/___.' >' "".
 *       | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *       \  \ `_.   \_ __\ /__ _/   .-` /  /
 *   =====`-.____`.___ \_____/___.-`___.-'=====
 *                     `=---='
 *   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *             佛祖开光         永无BUG
 * </pre>
 */
@Service
public class OauthServiceImpl extends BaseService implements OauthService{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Autowired(required=false)
    OauthClientDetailsMapperExt oauthClientDetailsMapper;

    @Autowired(required=false)
    UserRoleRelationMapperExt userRoleRelationMapper;

    public OauthSystemResponseDto queryClientDetailsById(String clientId) {
        OauthSystemResponseDto oauthSystemResponseDto = new OauthSystemResponseDto();
        OauthClientDetails oauthClientDetails = oauthClientDetailsMapper.selectByClientId(clientId);
        if (oauthClientDetails != null) {
            BeanUtils.copyProperties(oauthClientDetails, oauthSystemResponseDto);
        } else {
            throw new LogicException("未查到数据");
        }
        return oauthSystemResponseDto;
    }

    public List<String> queryRoleByUserId(Integer userId) {
        List<String> entity = new ArrayList<>();
        List<UserRoleRelation> userRoleRelationList = userRoleRelationMapper.queryRoleByUserId(userId);
        if (CollectionUtils.isNotEmpty(userRoleRelationList)) {
            entity = userRoleRelationList.stream().map(target -> String.valueOf(target.getRoleId())).collect(Collectors.toList());
            return entity;
        } else {
            throw new LogicException("未查到数据");
        }
    }

    public List<MetaDataSource> queryAllResourcesByRole() {
        // List<Object> params = Lists.newArrayList();
        // List<MetaDataSource> metaDataSourceList = new ArrayList<>();
        // List<OauthSystemResponseDto> oauthSystemResponseDtoList = this.findByNative("SELECT os.system_id AS systemId,os.client_id AS clientId,os.system_type AS systemType FROM oauth_system os", params, OauthSystemResponseDto.class);
        // for (OauthSystemResponseDto oauthSystemResponseDto : oauthSystemResponseDtoList) {
        //     if (oauthSystemResponseDto != null && oauthSystemResponseDto.getSystemType() != null) {
        //         if (oauthSystemResponseDto.getSystemType() == 1) {
        //             List<Object> paramss = Lists.newArrayList();
        //             List<RoleResourcesResponseDto> adminRoleResourceList = this.findByNative("SELECT ar.role_id AS roleId FROM admin_role ar", paramss, RoleResourcesResponseDto.class);
        //             for (RoleResourcesResponseDto roleResourcesResponseDto : adminRoleResourceList) {
        //                 List<String> urlList = new ArrayList<>();
        //                 MetaDataSource metaDataSource = new MetaDataSource();
        //                 AdminRoleResourceEntity responseVo = new AdminRoleResourceEntity();
        //                 responseVo.setRoleId(roleResourcesResponseDto.getRoleId());
        //                 org.springframework.data.domain.Example example = org.springframework.data.domain.Example.of(responseVo);
        //                 List<AdminRoleResourceEntity> oauthResourceEntityList = oauthClientRepository.findAll(example);
        //                 for (AdminRoleResourceEntity roleResourcesResponse : oauthResourceEntityList) {
        //                     List<Object> param = Lists.newArrayList();
        //                     param.add(roleResourcesResponse.getResourceId());
        //                     List<OauthResourceEntity> resourceEntityList = this.findByNative("SELECT o.url AS url FROM oauth_resource o where o.resource_id=?1", param, OauthResourceEntity.class);
        //                     if (CollectionUtils.isNotEmpty(resourceEntityList)) {
        //                         for (OauthResourceEntity oauthResourceEntity : resourceEntityList) {
        //                             String oauthResource = oauthResourceEntity.getUrl();
        //                             urlList.add(oauthResource);
        //                             metaDataSource.setUrls(urlList);
        //                         }
        //                     }
        //                     metaDataSource.setRole(String.valueOf(roleResourcesResponseDto.getRoleId()));
        //                     metaDataSource.setSystemType(1);
        //                 }
        //                 if (CollectionUtils.isNotEmpty(metaDataSource.getUrls())) {
        //                     metaDataSourceList.add(metaDataSource);
        //                 }
        //             }
        //         } else {
        //             List<Object> paramss = Lists.newArrayList();
        //             List<SystemResourceResponseDto> systemResourceResponseDtoList = this.findByNative("SELECT osr.resource_id AS resourceId,osr.system_id AS systemId FROM oauth_system_resource osr", paramss, SystemResourceResponseDto.class);
        //             MetaDataSource metaDataSource = new MetaDataSource();
        //             List<String> urlList = new ArrayList<>();
        //             for (SystemResourceResponseDto systemResourceResponseDto : systemResourceResponseDtoList) {
        //                 List<Object> param = Lists.newArrayList();
        //                 param.add(systemResourceResponseDto.getResourceId());
        //                 List<OauthResourceEntity> oauthResourceEntityList = this.findByNative("SELECT o.url AS url FROM oauth_resource o where o.resource_id=?1", param, OauthResourceEntity.class);
        //                 if (CollectionUtils.isNotEmpty(oauthResourceEntityList)) {
        //                     for (OauthResourceEntity oauthResourceEntity : oauthResourceEntityList) {
        //                         String oauthResource = oauthResourceEntity.getUrl();
        //                         urlList.add(oauthResource);
        //                         metaDataSource.setUrls(urlList);
        //                     }
        //                 }
        //                 metaDataSource.setRole(String.valueOf(systemResourceResponseDto.getSystemId()));
        //                 metaDataSource.setSystemType(2);
        //             }
        //             if (CollectionUtils.isNotEmpty(metaDataSource.getUrls())) {
        //                 metaDataSourceList.add(metaDataSource);
        //             }
        //         }
        //     }
        //
        // }
        // if (CollectionUtils.isNotEmpty(metaDataSourceList)) {
        //     return metaDataSourceList;
        // } else {
        //     throw new LogicException("未查到数据");
        // }
        return null;
    }


}
