package com.system.service;

import com.alibaba.fastjson.JSON;
import com.core.base.AbstractService;
import com.core.base.BaseRepository;
import com.google.common.collect.Lists;
import com.system.domain.DefaultRequestDto;
import com.system.domain.entity.AdminRoleEntity;
import com.system.domain.entity.AdminRoleResourceEntity;
import com.system.domain.entity.AdminStaffEntity;
import com.system.domain.request.role.RoleListRequestDto;
import com.system.domain.request.role.RoleRequestDto;
import com.system.domain.response.role.RoleListResponseDto;
import com.system.domain.response.role.RoleResourcesResponseDto;
import com.system.domain.response.role.RoleResult;
import com.system.repositories.RoleRepository;
import com.system.repositories.RoleRescourseRepository;
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
public class RoleService extends AbstractService<AdminStaffEntity, Integer> {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleRescourseRepository roleRescourseRepository;


    @Override
    protected BaseRepository getRepository() {
        return roleRepository;
    }


    /**
     * 用户列表 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public RoleListResponseDto list(RoleListRequestDto request) {
        List<RoleResult> roleResultList = new ArrayList<>();
        StringBuffer sbSql = new StringBuffer();
        StringBuffer sbCountSql = new StringBuffer();
        RoleListResponseDto roleListResponseDto = new RoleListResponseDto();
        Pageable pageable = new PageRequest(request.getCurrentPage() - 1, request.getPageSize());
        List<Object> params = Lists.newArrayList();
        String sql = "SELECT a.role_id AS roleId,a.create_time AS createTime,a.role_name AS roleName,a.role_remark AS roleRemark,a.update_time AS updateTime FROM admin_role a";
        String countSql = "select count(1) FROM admin_role";
        sbSql.append(sql);
        sbCountSql.append(countSql);
        Page<RoleResult> page = roleRepository.findByNative(sql, sbCountSql.toString(), params, pageable, RoleResult.class);
        // List<AdminRoleEntity> adminRoleEntityList = roleRepository.findAll();
        // roleResultList = adminRoleEntityList.stream().map(target -> {
        //     RoleResult result = new RoleResult();
        //     BeanUtils.copyProperties(target, result);
        //     return result;
        // }).collect(Collectors.toList());
        roleListResponseDto.setNextPage(page.hasNext());
        roleListResponseDto.setList(page.getContent());
        roleListResponseDto.setTotal(page.getTotalElements());
        roleListResponseDto.setTotalPage(page.getTotalPages());
        return roleListResponseDto;
    }


    public void edit(RoleRequestDto roleRequestDto) {
        AdminRoleEntity adminRoleEntity = new AdminRoleEntity();
        BeanUtils.copyProperties(roleRequestDto, adminRoleEntity);
        if (roleRequestDto.getRoleId() == null) {
            // 新增
            adminRoleEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            roleRepository.save(adminRoleEntity);
        } else {
            // 更新
            adminRoleEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            roleRepository.update(adminRoleEntity);
        }
    }

    /**
     * 删除角色 delete:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void delete(DefaultRequestDto request) {
        roleRepository.delete(request.getEntityId());
    }

    /**
     * 用户编辑或新增 editOrInsertUser:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public RoleResourcesResponseDto resourceList(DefaultRequestDto request) {
        RoleResourcesResponseDto roleResourcesResponseDto = new RoleResourcesResponseDto();
        AdminRoleResourceEntity adminRoleResourceEntity = new AdminRoleResourceEntity();
        List<Integer> roleResourcesResponseDtoList = new ArrayList<>();
        adminRoleResourceEntity.setRoleId(request.getEntityId());
        Example example = Example.of(adminRoleResourceEntity);
        List<AdminRoleResourceEntity> adminRoleResourceEntityList = roleRescourseRepository.findAll(example);
        if (CollectionUtils.isNotEmpty(adminRoleResourceEntityList)) {
            roleResourcesResponseDtoList = adminRoleResourceEntityList.stream().map(target -> target.getResourceId()).collect(Collectors.toList());
        }
        roleResourcesResponseDto.setResourcesList(roleResourcesResponseDtoList);
        return roleResourcesResponseDto;
    }

    /**
     * 用户限制 userDisable:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void resourceEdit(RoleListRequestDto request) {
        // 删除原先的权限
        AdminRoleResourceEntity adminRoleResourceEntity = new AdminRoleResourceEntity();
        adminRoleResourceEntity.setRoleId(request.getRoleId());
        Example example = Example.of(adminRoleResourceEntity);
        List<AdminRoleResourceEntity> adminStaffRoleEntityList = roleRescourseRepository.findAll(example);
        if (CollectionUtils.isNotEmpty(adminStaffRoleEntityList)) {
            for (AdminRoleResourceEntity roleResourceEntity : adminStaffRoleEntityList) {
                roleRescourseRepository.delete(roleResourceEntity.getRoleResourceId());
            }
        }
        // 新增
        List<Integer> resourceList = JSON.parseArray(String.valueOf(request.getResourceIdList()), Integer.class);
        if (CollectionUtils.isNotEmpty(resourceList)) {
            for (Integer resourceId : resourceList) {
                AdminRoleResourceEntity entity = new AdminRoleResourceEntity();
                entity.setRoleId(request.getRoleId());
                entity.setResourceId(resourceId);
                roleRescourseRepository.save(entity);
            }
        }

    }

}
