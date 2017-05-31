package com.system.service;

import com.core.base.AbstractService;
import com.core.base.BaseRepository;
import com.core.constants.enums.ResourcesEnum;
import com.core.exception.LogicException;
import com.core.utils.Regular;
import com.system.domain.DefaultRequestDto;
import com.system.domain.entity.AdminRoleResourceEntity;
import com.system.domain.entity.AdminStaffEntity;
import com.system.domain.entity.OauthResourceEntity;
import com.system.domain.request.resource.ResourcesListRequestDto;
import com.system.domain.request.resource.ResourcesRequestDto;
import com.system.domain.response.resource.ResourcesDetailResponseDto;
import com.system.domain.response.resource.ResourcesListResponseDto;
import com.system.domain.response.resource.ResourcesResponseDto;
import com.system.domain.response.resource.ResourcesResult;
import com.system.repositories.OauthResourceRepository;
import com.system.repositories.RoleRescourseRepository;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
public class ResourceService extends AbstractService<AdminStaffEntity, Integer> {

    @Autowired
    OauthResourceRepository oauthResourceRepository;

    @Autowired
    RoleRescourseRepository roleRescourseRepository;


    @Override
    protected BaseRepository getRepository() {
        return oauthResourceRepository;
    }


    /**
     * 用户列表 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public ResourcesListResponseDto list(ResourcesListRequestDto request) {
        List<String> whereClause = new ArrayList<>();
        ResourcesListResponseDto resourcesListResponseDto = new ResourcesListResponseDto();
        Pageable pageable = new PageRequest(request.getCurrentPage() - 1, request.getPageSize());
        List<Object> params = Lists.newArrayList();
        String sql = "SELECT o.resource_id AS resourceId,o.`code` AS `code`,o.`level` AS `level`,o.`name` AS `name`,o.page AS page,o.parent AS parent,o.sort AS sort,o.`status` AS `status`,o.system AS system,o.type AS type,o.url AS url FROM oauth_resource o";
        String countSql = "select count(1) FROM oauth_resource";
        if (request.getResourceId() != null) {
            params.add(request.getResourceId());
            whereClause.add("and o.resource_id=?" + params.size());
        }
        String sbSql = Regular.SplicedSql(sql, whereClause);
        String sbCountSql = Regular.SplicedSql(countSql, whereClause);
        Page<ResourcesResult> page = oauthResourceRepository.findByNative(sbSql.toString(), sbCountSql.toString(), params, pageable, ResourcesResult.class); //userRepository.findByNative()
        List<ResourcesResult> resourcesResultList = page.getContent().stream().map(target -> {
            ResourcesResult result = new ResourcesResult();
            BeanUtils.copyProperties(target, result);
            ResourcesEnum.ResourcesPidType levelEn = ResourcesEnum.ResourcesPidType.toEnumByV(target.getLevel());
            result.setLevelName(levelEn.getName());
            ResourcesEnum.ResourcesStatus statusEn = ResourcesEnum.ResourcesStatus.toEnumByV(target.getStatus());
            result.setStatusName(statusEn.getName());
            ResourcesEnum.ResourcesTypeEn typeEn = ResourcesEnum.ResourcesTypeEn.toEnumByV(target.getType());
            result.setTypeName(typeEn.getName());
            return result;
        }).collect(Collectors.toList());
        resourcesListResponseDto.setCurrentPage(page.getNumber());
        resourcesListResponseDto.setNextPage(page.hasNext());
        resourcesListResponseDto.setList(resourcesResultList);
        resourcesListResponseDto.setTotal(page.getTotalElements());
        resourcesListResponseDto.setTotalPage(page.getTotalPages());
        return resourcesListResponseDto;
    }

    /**
     * 权限编辑
     * editGoods:(这里用一句话描述这个方法的作用). <br/>
     *
     * @param request
     * @return
     * @author Maven
     * @date: 2016年11月10日 下午5:37:08
     */
    public void edit(ResourcesRequestDto request) {
        //插入
        if (request.getMode() == 0) {
            OauthResourceEntity record = new OauthResourceEntity();
            record.setResourceId(request.getResourceId());
            record.setParent(request.getParent());
            record.setName(request.getName());
            record.setLevel(request.getLevel());
            record.setUrl(request.getUrl());
            record.setSort(request.getSort());
            record.setCode(request.getCode());
            record.setType(request.getType());
            record.setPage(request.getPage());
            record.setStatus(request.getStatus());
            record.setSystem(request.getSystem());
            record.setCreateTime(new Timestamp(System.currentTimeMillis()));
            oauthResourceRepository.saveAndFlush(record);
            AdminRoleResourceEntity role = new AdminRoleResourceEntity();
            role.setRoleId(1);
            role.setResourceId(record.getResourceId());
            role.setCreateTime(new Timestamp(System.currentTimeMillis()));
            roleRescourseRepository.save(role);
        } else {
            //更新
            OauthResourceEntity record = new OauthResourceEntity();
            record.setResourceId(request.getResourceId());
            record.setParent(request.getParent());
            record.setName(request.getName());
            record.setLevel(request.getLevel());
            record.setUrl(request.getUrl());
            record.setSort(request.getSort());
            record.setCode(request.getCode());
            record.setType(request.getType());
            record.setPage(request.getPage());
            record.setStatus(request.getStatus());
            record.setSystem(request.getSystem());
            record.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            oauthResourceRepository.update(record);
        }
    }

    /**
     * 资源详情
     * editGoods:(这里用一句话描述这个方法的作用). <br/>
     *
     * @param request
     * @return
     * @author Maven
     * @date: 2016年11月10日 下午5:37:08
     */

    public ResourcesDetailResponseDto detail(DefaultRequestDto request) {
        ResourcesDetailResponseDto response = new ResourcesDetailResponseDto();
        List<ResourcesResponseDto> resourcesResultList = new ArrayList<>();
        OauthResourceEntity oauthResourceEntity = new OauthResourceEntity();
        oauthResourceEntity.setResourceId(request.getEntityId());
        Example example = Example.of(oauthResourceEntity);
        List<OauthResourceEntity> oauthResourceEntityList = oauthResourceRepository.findAll(example);
        if (CollectionUtils.isNotEmpty(oauthResourceEntityList)) {
            for (OauthResourceEntity resources : oauthResourceEntityList) {
                ResourcesResponseDto resourcesResponseDto = new ResourcesResponseDto();
                BeanUtils.copyProperties(resources, resourcesResponseDto);
                resourcesResponseDto.setResourceId(resources.getResourceId());
                ResourcesEnum.ResourcesPidType levelEn = ResourcesEnum.ResourcesPidType.toEnumByV(resources.getLevel());
                resourcesResponseDto.setLevelName(levelEn.getName());
                ResourcesEnum.ResourcesStatus statusEn = ResourcesEnum.ResourcesStatus.toEnumByV(resources.getStatus());
                resourcesResponseDto.setStatusName(statusEn.getName());
                ResourcesEnum.ResourcesTypeEn typeEn = ResourcesEnum.ResourcesTypeEn.toEnumByV(resources.getType());
                resourcesResponseDto.setTypeName(typeEn.getName());
                resourcesResultList.add(resourcesResponseDto);
            }
        }
        response.setResourcesResult(resourcesResultList);
        return response;
    }

    /**
     * 删除资源
     * editGoods:(这里用一句话描述这个方法的作用). <br/>
     *
     * @param request
     * @return
     * @author Maven
     * @date: 2016年11月10日 下午5:37:08
     */
    public void delete(DefaultRequestDto request) {
        try {
            oauthResourceRepository.delete(request.getEntityId());
        } catch (Exception e) {
            throw new LogicException("页面已删除");
        }
    }

    /**
     * 资源搜索
     * editGoods:(这里用一句话描述这个方法的作用). <br/>
     *
     * @param request
     * @return
     * @author Maven
     * @date: 2016年11月10日 下午5:37:08
     */
    public ResourcesDetailResponseDto search(ResourcesRequestDto request) {
        ResourcesDetailResponseDto response = new ResourcesDetailResponseDto();
        List<ResourcesResponseDto> ResourcesResultList = new ArrayList<>();
        OauthResourceEntity oauthResourceEntity = new OauthResourceEntity();
        oauthResourceEntity.setName(request.getName());
        Example example = Example.of(oauthResourceEntity);
        List<OauthResourceEntity> resourcesList = oauthResourceRepository.findAll(example);
        if (CollectionUtils.isNotEmpty(resourcesList)) {
            for (OauthResourceEntity as : resourcesList) {
                ResourcesResponseDto ResourcesResult = new ResourcesResponseDto();
                ResourcesResult.setResourceId(as.getResourceId());
                ResourcesResult.setName(as.getName());
                ResourcesResultList.add(ResourcesResult);
            }
        }
        response.setResourcesResult(ResourcesResultList);
        return response;
    }


    /**
     * queryAdminResources:(修改或新增时树型结构). <br/>
     *
     * @param resourcesRequestDto
     * @return
     * @author "maven"
     */
    public List<ResourcesDetailResponseDto> editList(ResourcesRequestDto resourcesRequestDto) {
        List<OauthResourceEntity> oauthResourceEntityList = oauthResourceRepository.findAll();
        List<ResourcesDetailResponseDto> resourcesDetailResponseDtoList = oauthResourceEntityList.stream().map(target -> {
            ResourcesDetailResponseDto result = new ResourcesDetailResponseDto();
            BeanUtils.copyProperties(target, result);
            return result;
        }).collect(Collectors.toList());
        return resourcesDetailResponseDtoList;
    }
}
