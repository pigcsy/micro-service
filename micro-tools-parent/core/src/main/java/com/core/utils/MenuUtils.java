/**
 * Project Name:saas-authentication
 * File Name:MenuFormat.java
 * Package Name:com.saas.authentication.support
 * Date:2016年10月19日下午8:44:19
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.core.utils;

import com.core.constants.enums.AuthResourcesEnums;
import com.core.domain.Menu;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ClassName: MenuFormat <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author 半轴
 * @date: 2016年10月19日 下午8:44:19 <br/>
 * @since JDK 1.8
 */
public class MenuUtils {

    /**
     * 格式化菜单
     *
     * @param beginLevel 开始菜单等级
     * @param sources
     * @return
     */
    public static Set<Menu> format(final AuthResourcesEnums.LevelEnum beginLevel, Collection<Menu> sources) {
        Set<Menu> menus = sources.stream().filter(target -> beginLevel.getValue() == target.getLevel())
                .map(target -> new Menu(target.getResourceId(), target.getUrl(), target.getPage(),
                        target.getNm(), target.getSort(), target.getLevel(), target.getParent(), target.getIcon(), target.getCode())).sorted().collect(Collectors.toSet());
        menus.forEach(target -> {
            target.setSubMenu(matchSubMenu(target, sources));
        });
        return menus;
    }

    /**
     * 匹配子菜单
     *
     * @param parentMenu
     * @param resources
     */
    public static List<Menu> matchSubMenu(Menu parentMenu, final Collection<Menu> resources) {
        List<Menu> subMenus = resources.stream().filter(target -> parentMenu.getResourceId() == target.getParent())
                .map(target -> new Menu(target.getResourceId(), target.getUrl(), target.getPage(),
                        target.getNm(), target.getSort(), target.getLevel(), target.getParent(), target.getIcon(), target.getCode()))
                .sorted().collect(Collectors.toList());
        subMenus.forEach(target -> {
            List<Menu> tempMenus = matchSubMenu(target, resources);
            if (CollectionUtils.isNotEmpty(tempMenus)) {
                target.setSubMenu(tempMenus);
            }
        });
        return subMenus;
    }

    public static List<Menu> format(List<Menu> menus) {
        List<Menu> lists = new ArrayList<>();
        menus.stream().filter(menu -> AuthResourcesEnums.LevelEnum.FIRST.getValue() == menu.getLevel())
                .forEach(parent -> {
                    lists.add(parent);
                    matchSubResource(parent, menus);// 寻找子节点
                    menus.add(parent);
                });
        Collections.sort(menus);
        return menus;
    }

    private final static void matchSubResource(Menu parent, List<Menu> menus) {
        List<Menu> subMenus = new ArrayList<Menu>();
        menus.stream().filter(menu -> parent.getResourceId() == menu.getParent()).forEach(menu -> {
            matchSubResource(menu, menus);
            subMenus.add(menu);
        });
        parent.setSubMenu(subMenus);
    }

}
