package com.system.service;

import com.alibaba.fastjson.JSON;
import com.core.base.AbstractService;
import com.core.base.BaseRepository;
import com.core.constants.enums.UserEnum;
import com.core.exception.LogicException;
import com.core.utils.Regular;
import com.google.common.collect.Lists;
import com.system.domain.DefaultRequestDto;
import com.system.domain.entity.AdminStaffEntity;
import com.system.domain.entity.AdminStaffRoleEntity;
import com.system.domain.request.user.UserListRequestDto;
import com.system.domain.request.user.UserRequestDto;
import com.system.domain.response.user.UserListResponseDto;
import com.system.domain.response.user.UserResult;
import com.system.domain.response.user.UserRoleResponseDto;
import com.system.repositories.UserRepository;
import com.system.repositories.UserRoleRepository;
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
public class UserService extends AbstractService<AdminStaffEntity, Integer> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;


    @Override
    protected BaseRepository getRepository() {
        return userRepository;
    }


    /**
     * 用户列表 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public UserListResponseDto list(UserListRequestDto request) {
        List<String> whereClause = new ArrayList<>();
        UserListResponseDto userListResponseDto = new UserListResponseDto();
        Pageable pageable = new PageRequest(request.getCurrentPage() - 1, request.getPageSize());
        List<Object> params = Lists.newArrayList();
        String sql = "SELECT a.staff_id AS staffId,a.code AS code,a.pwd AS pwd,a.staff_roster AS staffRoster,a.staff_name AS staffName,a.depart_id AS departId,a.staff_post AS staffPost,a.staff_phone AS staffPhone, a.staff_email AS staffEmail, a.ins_tm AS insTm, a.leader AS leader, a.status AS status, ad.name AS departName FROM admin_staff a LEFT JOIN admin_depart ad ON a.depart_id = ad.depart_id ";
        String countSql = "select count(1) FROM admin_staff a LEFT JOIN admin_depart ad ON a.depart_id = ad.depart_id  ";
        if (request.getDepartId() != null) {
            params.add(request.getDepartId());
            whereClause.add("and a.depart_id=?" + params.size());
        }
        if (request.getStaffPhone() != null) {
            params.add(request.getStaffPhone());
            whereClause.add("and a.staff_phone=?" + params.size());
        }
        if (request.getStaffRoster() != null) {
            params.add("%" + request.getStaffRoster() + "%");
            whereClause.add("and a.staff_roster like " + "?" + params.size());
        }
        String sbSql = Regular.SplicedSql(sql, whereClause);
        String sbCountSql = Regular.SplicedSql(countSql, whereClause);
        Page<UserResult> page = userRepository.findByNative(sbSql.toString(), sbCountSql.toString(), params, pageable, UserResult.class); //userRepository.findByNative()
        userListResponseDto.setCurrentPage(page.getNumber());
        userListResponseDto.setNextPage(page.hasNext());
        userListResponseDto.setList(page.getContent());
        userListResponseDto.setTotal(page.getTotalElements());
        userListResponseDto.setTotalPage(page.getTotalPages());
        return userListResponseDto;
    }


    /**
     * 用户详情 detail:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public UserResult detail(DefaultRequestDto request) {
        UserResult userResult = new UserResult();
        AdminStaffEntity adminStaffEntity = userRepository.getOne(request.getEntityId());
        BeanUtils.copyProperties(adminStaffEntity, userResult);
        return userResult;
    }

    /**
     * 用户编辑或新增 editOrInsertUser:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void editOrInsertUser(UserRequestDto request) {
        if (request.getStaffId() != null) {
            AdminStaffEntity adminStaffEntity = new AdminStaffEntity();
            BeanUtils.copyProperties(request, adminStaffEntity);
            userRepository.update(adminStaffEntity);
        } else {
            // 验证花名和工号
            AdminStaffEntity adminStaffEntity = new AdminStaffEntity();
            BeanUtils.copyProperties(request, adminStaffEntity);
            boolean vail = checkUser(adminStaffEntity);
            if (!vail) {
                logger.error("员工工号或花名重复，code:" + request.getCode());
                throw new LogicException("员工工号或花名重复");
            } else {
                adminStaffEntity.setSystemId(9);
                adminStaffEntity.setStatus((byte) 1);
                userRepository.save(adminStaffEntity);
            }
        }
    }


    public boolean checkUser(AdminStaffEntity adminStaffEntity) {
        AdminStaffEntity query = new AdminStaffEntity();
        query.setCode(adminStaffEntity.getCode());
        query.setStaffRoster(adminStaffEntity.getStaffRoster());
        Example example = Example.of(query);
        List<AdminStaffEntity> adminStaffEntityList = userRepository.findAll(example);
        if (CollectionUtils.isEmpty(adminStaffEntityList))
            return true;
        if (adminStaffEntity.getStaffId() == null)
            return false;
        for (AdminStaffEntity as : adminStaffEntityList) {
            if (!adminStaffEntity.getStaffId().equals(as.getStaffId())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 用户限制 disable:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void disable(DefaultRequestDto request) {
        if (request == null) {
            throw new LogicException("员工id未取得");
        }
        AdminStaffEntity result = userRepository.findOne(request.getEntityId());
        if (result != null) {
            if (UserEnum.StaffStatus.HASLFT.getValue() == result.getStatus()) {
                logger.error("员工已离职，id：" + request.getEntityId());
                throw new LogicException("员工状态已改变，请刷新页面。");
            }
        }
        userRepository.delete(request.getEntityId());
    }

    /**
     * 用户角色列表 userDisable:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public List<UserRoleResponseDto> userRoleList(DefaultRequestDto request) {
        AdminStaffRoleEntity adminStaffRoleEntity = new AdminStaffRoleEntity();
        adminStaffRoleEntity.setStaffId(request.getEntityId());
        Example example = Example.of(adminStaffRoleEntity);
        List<AdminStaffRoleEntity> adminStaffRoleEntityList = userRoleRepository.findAll(example);
        List<UserRoleResponseDto> userEntityList = adminStaffRoleEntityList.stream().map(target -> {
            UserRoleResponseDto result = new UserRoleResponseDto();
            result.setRoleId(target.getRoleId());
            result.setStaffId(target.getStaffId());
            return result;
        }).collect(Collectors.toList());
        return userEntityList;
    }

    /**
     * 用户角色编辑 userDisable:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void editUserRole(UserRequestDto request) {
        List<Integer> roleList = JSON.parseArray(String.valueOf(request.getRoleIdList()), Integer.class);
        AdminStaffRoleEntity adminStaffRoleEntity = new AdminStaffRoleEntity();
        adminStaffRoleEntity.setStaffId(request.getStaffId());
        Example example = Example.of(adminStaffRoleEntity);
        List<AdminStaffRoleEntity> adminStaffRoleEntityList = userRoleRepository.findAll(example);
        if (CollectionUtils.isNotEmpty(adminStaffRoleEntityList)) {
            for (AdminStaffRoleEntity staffRoleEntity : adminStaffRoleEntityList) {
                userRoleRepository.delete(staffRoleEntity.getStaffRoleId());
            }
        }
        if (CollectionUtils.isNotEmpty(roleList)) {
            for (Integer role : roleList) {
                AdminStaffRoleEntity adminStaffRole = new AdminStaffRoleEntity();
                adminStaffRole.setStaffId(request.getStaffId());
                adminStaffRole.setRoleId(role);
                adminStaffRole.setCreateTime(new Timestamp(System.currentTimeMillis()));
                userRoleRepository.save(adminStaffRole);
            }

        }

    }


}
