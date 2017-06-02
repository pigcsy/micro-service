package com.system.service;

import com.cache.support.vo.redis.resource.MetaDataSource;
import com.core.base.AbstractService;
import com.core.base.BaseRepository;
import com.core.exception.LogicException;
import com.google.common.collect.Lists;
import com.system.domain.entity.AdminRoleResourceEntity;
import com.system.domain.entity.AdminStaffEntity;
import com.system.domain.entity.OauthClientDetailsEntity;
import com.system.domain.entity.OauthResourceEntity;
import com.system.domain.entity.OauthSystemEntity;
import com.system.domain.response.oath.OauthSystemResponseDto;
import com.system.domain.response.oath.SystemResourceResponseDto;
import com.system.domain.response.resource.ResourcesResponseDto;
import com.system.domain.response.role.RoleResourcesResponseDto;
import com.system.domain.response.user.UserResult;
import com.system.domain.response.user.UserRoleResponseDto;
import com.system.repositories.OauthClientRepository;
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
public class OauthService extends AbstractService<OauthClientDetailsEntity, String> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    OauthClientRepository oauthClientRepository;

    @Override
    protected BaseRepository<OauthClientDetailsEntity, String> getRepository() {
        return oauthClientRepository;
    }

    /**
     * 根据用户名查询用户信息 list:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author maven
     */
    public UserResult queryByName(String code) {
        UserResult userResult = new UserResult();
        List<Object> params = Lists.newArrayList();
        params.add(code);
        String querySql = "SELECT a.staff_id AS staffId,a.code AS code,a.pwd AS pwd,a.staff_roster AS staffRoster,a.depart_id AS departId," +
                "a.staff_post AS staffPost,a.staff_phone AS staffPhone, a.staff_email AS staffEmail, a.ins_tm AS insTm, " +
                "a.leader AS leader, a.status AS status ,a.system_id AS systemId, a.daily_access_num as dailyAccessNum, a.minute_access_num as minuteAccessNum" +
                " FROM admin_staff a where a.code = ?1 ";
        List<AdminStaffEntity> adminStaffEntityList = this.findByNative(querySql, params, AdminStaffEntity.class);
        if (CollectionUtils.isNotEmpty(adminStaffEntityList)) {
            for (AdminStaffEntity adminStaffEntity : adminStaffEntityList) {
                BeanUtils.copyProperties(adminStaffEntity, userResult);
            }
            return userResult;
        } else {
            throw new LogicException("未查到数据");
        }
    }

    public List<ResourcesResponseDto> queryResourcesById(Integer staffId) {
        List<ResourcesResponseDto> resourcesResponseDto = new ArrayList<>();
        List<Object> params = Lists.newArrayList();
        params.add(staffId);
        List<OauthResourceEntity> oauthResourceEntityList = this.findByNative("SELECT o.url AS url FROM admin_staff_role asr LEFT JOIN admin_role_resource arr ON asr.role_id = arr.role_id LEFT JOIN oauth_resource o ON o.resource_id = arr.resource_id WHERE asr.staff_id= ?1 ", params, OauthResourceEntity.class);
        if (CollectionUtils.isNotEmpty(oauthResourceEntityList)) {
            resourcesResponseDto = oauthResourceEntityList.stream().map(target -> {
                ResourcesResponseDto result = new ResourcesResponseDto();
                result.setUrl(target.getUrl());
                return result;
            }).collect(Collectors.toList());
        } else {
            throw new LogicException("未查到数据");
        }

        return resourcesResponseDto;
    }


    public OauthSystemResponseDto queryClientDetailsById(String clientId) {
        OauthSystemResponseDto oauthSystemResponseDto = new OauthSystemResponseDto();
        List<Object> params = Lists.newArrayList();
        params.add(clientId);
        String querySql = "SELECT os.client_id AS clientId,os.system_id AS systemId,os.system_type AS systemType," +
                "os.system_remark AS systemRemark,os.system_name AS systemName, os.daily_access_num as dailyAccessNum, os.minute_access_num as minuteAccessNum" +
                " FROM oauth_system os WHERE os.client_id =?1";
        List<OauthSystemEntity> oauthSystemEntityList = this.findByNative(querySql, params, OauthSystemEntity.class);
        if (CollectionUtils.isNotEmpty(oauthSystemEntityList)) {
            for (OauthSystemEntity oauthResourceEntity : oauthSystemEntityList) {
                BeanUtils.copyProperties(oauthResourceEntity, oauthSystemResponseDto);
            }
        } else {
            throw new LogicException("未查到数据");
        }
        return oauthSystemResponseDto;
    }


    public List<String> queryRoleByStaffId(Integer staffId) {
        List<Object> params = Lists.newArrayList();
        List<String> entity = new ArrayList<>();
        params.add(staffId);
        List<UserRoleResponseDto> list = this.findByNative("SELECT asr.role_id AS roleId FROM admin_staff_role asr WHERE asr.staff_id= ?1", params, UserRoleResponseDto.class);
        if (CollectionUtils.isNotEmpty(list)) {
            entity = list.stream().map(target -> String.valueOf(target.getRoleId())).collect(Collectors.toList());
            return entity;
        } else {
            throw new LogicException("未查到数据");
        }
    }

    public List<MetaDataSource> queryAllResourcesByRole() {
        List<Object> params = Lists.newArrayList();
        List<MetaDataSource> metaDataSourceList = new ArrayList<>();
        List<OauthSystemResponseDto> oauthSystemResponseDtoList = this.findByNative("SELECT os.system_id AS systemId,os.client_id AS clientId,os.system_type AS systemType FROM oauth_system os", params, OauthSystemResponseDto.class);
        for (OauthSystemResponseDto oauthSystemResponseDto : oauthSystemResponseDtoList) {
            if (oauthSystemResponseDto != null && oauthSystemResponseDto.getSystemType() != null) {
                if (oauthSystemResponseDto.getSystemType() == 1) {
                    List<Object> paramss = Lists.newArrayList();
                    List<RoleResourcesResponseDto> adminRoleResourceList = this.findByNative("SELECT ar.role_id AS roleId FROM admin_role ar", paramss, RoleResourcesResponseDto.class);
                    for (RoleResourcesResponseDto roleResourcesResponseDto : adminRoleResourceList) {
                        List<String> urlList = new ArrayList<>();
                        MetaDataSource metaDataSource = new MetaDataSource();
                        AdminRoleResourceEntity responseVo = new AdminRoleResourceEntity();
                        responseVo.setRoleId(roleResourcesResponseDto.getRoleId());
                        org.springframework.data.domain.Example example = org.springframework.data.domain.Example.of(responseVo);
                        List<AdminRoleResourceEntity> oauthResourceEntityList = oauthClientRepository.findAll(example);
                        for (AdminRoleResourceEntity roleResourcesResponse : oauthResourceEntityList) {
                            List<Object> param = Lists.newArrayList();
                            param.add(roleResourcesResponse.getResourceId());
                            List<OauthResourceEntity> resourceEntityList = this.findByNative("SELECT o.url AS url FROM oauth_resource o where o.resource_id=?1", param, OauthResourceEntity.class);
                            if (CollectionUtils.isNotEmpty(resourceEntityList)) {
                                for (OauthResourceEntity oauthResourceEntity : resourceEntityList) {
                                    String oauthResource = oauthResourceEntity.getUrl();
                                    urlList.add(oauthResource);
                                    metaDataSource.setUrls(urlList);
                                }
                            }
                            metaDataSource.setRole(String.valueOf(roleResourcesResponseDto.getRoleId()));
                            metaDataSource.setSystemType(1);
                        }
                        if (CollectionUtils.isNotEmpty(metaDataSource.getUrls())) {
                            metaDataSourceList.add(metaDataSource);
                        }
                    }
                } else {
                    List<Object> paramss = Lists.newArrayList();
                    List<SystemResourceResponseDto> systemResourceResponseDtoList = this.findByNative("SELECT osr.resource_id AS resourceId,osr.system_id AS systemId FROM oauth_system_resource osr", paramss, SystemResourceResponseDto.class);
                    MetaDataSource metaDataSource = new MetaDataSource();
                    List<String> urlList = new ArrayList<>();
                    for (SystemResourceResponseDto systemResourceResponseDto : systemResourceResponseDtoList) {
                        List<Object> param = Lists.newArrayList();
                        param.add(systemResourceResponseDto.getResourceId());
                        List<OauthResourceEntity> oauthResourceEntityList = this.findByNative("SELECT o.url AS url FROM oauth_resource o where o.resource_id=?1", param, OauthResourceEntity.class);
                        if (CollectionUtils.isNotEmpty(oauthResourceEntityList)) {
                            for (OauthResourceEntity oauthResourceEntity : oauthResourceEntityList) {
                                String oauthResource = oauthResourceEntity.getUrl();
                                urlList.add(oauthResource);
                                metaDataSource.setUrls(urlList);
                            }
                        }
                        metaDataSource.setRole(String.valueOf(systemResourceResponseDto.getSystemId()));
                        metaDataSource.setSystemType(2);
                    }
                    if (CollectionUtils.isNotEmpty(metaDataSource.getUrls())) {
                        metaDataSourceList.add(metaDataSource);
                    }
                }
            }

        }
        if (CollectionUtils.isNotEmpty(metaDataSourceList)) {
            return metaDataSourceList;
        } else {
            throw new LogicException("未查到数据");
        }
    }


}
