package com.system.service;

import com.core.base.BaseService;
import com.core.exception.LogicException;
import com.entity.UserBase;
import com.mapper.ext.UserBaseMapperExt;
import com.system.domain.DefaultRequestDto;
import com.system.domain.request.user.UserListRequestDto;
import com.system.domain.request.user.UserRequestDto;
import com.system.domain.response.user.UserListResponseDto;
import com.system.domain.response.user.UserResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserService extends BaseService {


    @Autowired
    UserBaseMapperExt userBaseMapper;

    /**
     * 用户列表 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public UserListResponseDto list(UserListRequestDto request) {
        return null;
    }


    /**
     * 用户详情 detail:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public UserResult detail(DefaultRequestDto request) {
        UserResult userResult = new UserResult();
        UserBase userBase = userBaseMapper.selectByPrimaryKey(request.getEntityId());
        BeanUtils.copyProperties(userBase, userResult);
        return userResult;
    }

    /**
     * 用户编辑或新增 editOrInsertUser:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void editOrInsertUser(UserRequestDto request) {
        // if (request.getStaffId() != null) {
        //     AdminStaffEntity adminStaffEntity = new AdminStaffEntity();
        //     BeanUtils.copyProperties(request, adminStaffEntity);
        //     userRepository.update(adminStaffEntity);
        // } else {
        //     // 验证花名和工号
        //     AdminStaffEntity adminStaffEntity = new AdminStaffEntity();
        //     BeanUtils.copyProperties(request, adminStaffEntity);
        //     boolean vail = checkUser(adminStaffEntity);
        //     if (!vail) {
        //         logger.error("员工工号或花名重复，code:" + request.getCode());
        //         throw new LogicException("员工工号或花名重复");
        //     } else {
        //         adminStaffEntity.setSystemId(9);
        //         adminStaffEntity.setStatus((byte) 1);
        //         userRepository.save(adminStaffEntity);
        //     }
        // }
    }


    public boolean checkUser() {//AdminStaffEntity adminStaffEntity
        // AdminStaffEntity query = new AdminStaffEntity();
        // query.setCode(adminStaffEntity.getCode());
        // query.setStaffRoster(adminStaffEntity.getStaffRoster());
        // Example example = Example.of(query);
        // List<AdminStaffEntity> adminStaffEntityList = userRepository.findAll(example);
        // if (CollectionUtils.isEmpty(adminStaffEntityList))
        //     return true;
        // if (adminStaffEntity.getStaffId() == null)
        //     return false;
        // for (AdminStaffEntity as : adminStaffEntityList) {
        //     if (!adminStaffEntity.getStaffId().equals(as.getStaffId())) {
        //         return false;
        //     }
        // }
        return true;
    }

    /**
     * 用户限制 disable:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void disable(DefaultRequestDto request) {
        // if (request == null) {
        //     throw new LogicException("员工id未取得");
        // }
        // AdminStaffEntity result = userRepository.findOne(request.getEntityId());
        // if (result != null) {
        //     if (UserEnum.StaffStatus.HASLFT.getValue() == result.getStatus()) {
        //         logger.error("员工已离职，id：" + request.getEntityId());
        //         throw new LogicException("员工状态已改变，请刷新页面。");
        //     }
        // }
        // userRepository.delete(request.getEntityId());
    }


    /**
     * 根据用户名查询用户信息 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public UserResult queryByName(String code) {
        UserResult userResult = new UserResult();
        UserBase userBase = userBaseMapper.selectByName(code);
        if (userBase != null) {
            BeanUtils.copyProperties(userBase, userResult);
            return userResult;
        } else {
            throw new LogicException("未查到数据");
        }
    }
}
