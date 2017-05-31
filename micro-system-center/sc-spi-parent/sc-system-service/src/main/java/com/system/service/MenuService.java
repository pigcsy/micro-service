package com.system.service;

import com.core.base.AbstractService;
import com.core.base.BaseRepository;
import com.core.constants.enums.AuthResourcesEnums;
import com.core.domain.Menu;
import com.core.domain.Role;
import com.core.exception.LogicException;
import com.core.support.security.CurrentPrincipalHolder;
import com.core.utils.MenuUtils;
import com.google.common.collect.Lists;
import com.system.domain.entity.AdminStaffEntity;
import com.system.domain.entity.OauthResourceEntity;
import com.system.domain.response.menu.MenuResponseDto;
import com.system.domain.response.user.UserResult;
import com.system.repositories.OauthResourceRepository;
import com.system.repositories.UserRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class MenuService extends AbstractService<AdminStaffEntity, Integer> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OauthResourceRepository oauthResourceRepository;


    @Override
    protected BaseRepository getRepository() {
        return userRepository;
    }


    /**
     * 用户 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public MenuResponseDto menu() {
        CurrentPrincipalHolder.getPrincipal();
        UserResult userResult = user(CurrentPrincipalHolder.getPrincipal().getUid());
        MenuResponseDto response = new MenuResponseDto();
        response.setCode(userResult.getCode());
        response.setStaffId(userResult.getStaffId());
        response.setName(userResult.getStaffName());
        response.setRoster(userResult.getStaffRoster());
        response.setLoginTm(new Date());
        response.setRole(role(CurrentPrincipalHolder.getPrincipal().getUid()));
        if (menu(CurrentPrincipalHolder.getPrincipal().getUid()) != null) {
            response.setMenus(menu(CurrentPrincipalHolder.getPrincipal().getUid()).stream().sorted().collect(Collectors.toList()));
        } else {
            throw new LogicException("该用户未设置角色");
        }
        return response;
    }


    /**
     * 用户 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public UserResult user(Integer staffId) {
        UserResult userResult = new UserResult();
        AdminStaffEntity adminStaffEntity = new AdminStaffEntity();
        adminStaffEntity.setStaffId(staffId);
        Example example = Example.of(adminStaffEntity);
        AdminStaffEntity user = userRepository.findOne(example);
        BeanUtils.copyProperties(user, userResult);
        return userResult;
    }


    /**
     * 角色 detail:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public Set<Role> role(Integer entity) {
        List<Object> params = Lists.newArrayList();
        params.add(entity);
        List<OauthResourceEntity> oauthResourceEntityList = oauthResourceRepository.findByNative("SELECT  ar.resource_id AS resourceId,ar.url AS url,ar.page AS page,ar.`level` AS level,ar.`code` AS code, ar.parent AS parent,ar.`name` AS name,ar.sort AS sort,ar.type AS type FROM admin_staff_role sr,oauth_resource ar,admin_role_resource rr WHERE sr.role_id = rr.role_id  AND ar.resource_id=rr.resource_id AND ar.status = '1'  AND sr.staff_id=?1 ", params, OauthResourceEntity.class);
        if (CollectionUtils.isEmpty(oauthResourceEntityList)) {
            return null;
        } else {
            return oauthResourceEntityList.stream().collect(() -> new HashSet<Role>(), (list, resouce) -> list.add(new Role(resouce.getCode(), resouce.getUrl())), (list1, list2) -> list1.addAll(list2));
        }
    }

    /**
     * 菜单 editOrInsertUser:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public List<Menu> menu(Integer entity) {
        List<Object> params = Lists.newArrayList();
        params.add(entity);
        List<OauthResourceEntity> oauthResourceEntityList = oauthResourceRepository.findByNative("SELECT  ar.resource_id AS resourceId,ar.url AS url,ar.page AS page,ar.`level` AS level,ar.`code` AS code, ar.parent AS parent,ar.`name` AS name,ar.sort AS sort,ar.type AS type FROM admin_staff_role sr,oauth_resource ar,admin_role_resource rr WHERE sr.role_id = rr.role_id  AND ar.resource_id=rr.resource_id AND ar.status = '1'  AND sr.staff_id=?1 ", params, OauthResourceEntity.class);
        if (CollectionUtils.isEmpty(oauthResourceEntityList))
            return null;
        Set<Menu> menus = oauthResourceEntityList.stream().map(resource -> new Menu(resource.getResourceId(), resource.getUrl(), resource.getPage(),
                resource.getName(), resource.getSort(), resource.getLevel(), resource.getParent())).collect(Collectors.toSet());
        return MenuUtils.format(AuthResourcesEnums.LevelEnum.SECOND, menus).stream().collect(Collectors.toList());
    }


}
