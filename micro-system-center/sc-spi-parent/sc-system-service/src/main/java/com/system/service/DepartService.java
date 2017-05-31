package com.system.service;

import com.core.base.AbstractService;
import com.core.base.BaseRepository;
import com.core.exception.LogicException;
import com.system.domain.DefaultRequestDto;
import com.system.domain.entity.AdminDepartEntity;
import com.system.domain.entity.AdminStaffEntity;
import com.system.domain.request.depart.DepartListRequestDto;
import com.system.domain.request.depart.DepartRequestDto;
import com.system.domain.response.depart.DepartListResponseDto;
import com.system.domain.response.depart.DepartResultDto;
import com.system.repositories.DepartRepository;
import com.system.repositories.UserRepository;
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
public class DepartService extends AbstractService<AdminStaffEntity, Integer> {

    @Autowired
    DepartRepository departRepository;
    @Autowired
    UserRepository userRepository;


    @Override
    protected BaseRepository getRepository() {
        return departRepository;
    }


    /**
     * 用户列表 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public DepartListResponseDto list(DepartListRequestDto request) {
        StringBuffer sbSql = new StringBuffer();
        StringBuffer sbCountSql = new StringBuffer();
        DepartListResponseDto departListResponseDto = new DepartListResponseDto();
        Pageable pageable = new PageRequest(request.getCurrentPage() - 1, request.getPageSize());
        List<Object> params = Lists.newArrayList();
        String sql = "SELECT ad.`depart_id` AS departId, ad.`parent` AS parent, ad.`name` AS name, ad.`type` AS type, ad2.`name` AS parentName FROM admin_depart ad LEFT JOIN admin_depart ad2 ON ad.parent = ad2.depart_id";
        String countSql = "select count(1) FROM admin_depart ad LEFT JOIN admin_depart ad2 ON ad.parent = ad2.depart_id";
        sbSql.append(sql);
        sbCountSql.append(countSql);
        Page<DepartResultDto> page = departRepository.findByNative(sbSql.toString(), sbCountSql.toString(), params, pageable, DepartResultDto.class); //userRepository.findByNative()
        departListResponseDto.setCurrentPage(page.getNumber());
        departListResponseDto.setNextPage(page.hasNext());
        departListResponseDto.setList(page.getContent());
        departListResponseDto.setTotal(page.getTotalElements());
        departListResponseDto.setTotalPage(page.getTotalPages());
        return departListResponseDto;
    }


    /**
     * 用户详情 detail:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author maven
     */
    public DepartResultDto detail(DefaultRequestDto request) {
        DepartResultDto departResultDto = new DepartResultDto();
        departResultDto.setDepartId(request.getEntityId());
        List<Object> params = Lists.newArrayList();
        params.add(request.getEntityId());
        String sql = "SELECT ad.`depart_id` AS departId, ad.`parent` AS parent, ad.name AS name, ad.`type` AS type, ad2.name AS parentName FROM admin_depart ad LEFT JOIN admin_depart ad2 ON ad.parent = ad2.depart_id where ad.depart_id=?1";
        List<DepartResultDto> departResultDtoList = departRepository.findByNative(sql, params, DepartResultDto.class);
        if (CollectionUtils.isNotEmpty(departResultDtoList)) {
            for (DepartResultDto adminDepartEntity : departResultDtoList) {
                BeanUtils.copyProperties(adminDepartEntity, departResultDto);
            }
        }
        return departResultDto;
    }

    /**
     * 用户编辑或新增 editOrInsertUser:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void edit(DepartRequestDto request) {
        AdminDepartEntity adminDepartEntity = new AdminDepartEntity();
        BeanUtils.copyProperties(request, adminDepartEntity);
        if (request.getDepartId() == null) {
            // 新增
            adminDepartEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            departRepository.save(adminDepartEntity);
        } else {
            // 更新
            adminDepartEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            departRepository.update(adminDepartEntity);
        }
    }

    /**
     * 删除部门
     * delete:(这里用一句话描述这个方法的作用). <br/>
     *
     * @param request
     * @return
     * @author Maven
     * @date: 2016年11月10日 下午5:37:08
     */
    public void delete(DefaultRequestDto request) {
        AdminStaffEntity adminStaffEntity = new AdminStaffEntity();
        adminStaffEntity.setDepartId(request.getEntityId());
        Example example = Example.of(adminStaffEntity);
        // 有员工的部门不能删除
        List<AdminStaffEntity> list = userRepository.findAll(example);
        if (CollectionUtils.isNotEmpty(list)) {
            logger.error("该部门存在员工，id：" + example);
            throw new LogicException("有员工的部门不能删除");
        }
        try {
            departRepository.delete(request.getEntityId());
        } catch (Exception e) {
            throw new LogicException("部门已删除");
        }
    }

    /**
     * 用户列表 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public List<DepartResultDto> editList(DepartRequestDto request) {
        AdminDepartEntity adminDepartEntity = new AdminDepartEntity();
        adminDepartEntity.setName(request.getName());
        adminDepartEntity.setDepartId(request.getDepartId());
        Example example = Example.of(adminDepartEntity);
        List<AdminDepartEntity> adminDepartEntities = departRepository.findAll(example);
        List<DepartResultDto> departListResponseDto = adminDepartEntities.stream().map(target -> {
            DepartResultDto result = new DepartResultDto();
            BeanUtils.copyProperties(target, result);
            return result;
        }).collect(Collectors.toList());
        return departListResponseDto;
    }
}
