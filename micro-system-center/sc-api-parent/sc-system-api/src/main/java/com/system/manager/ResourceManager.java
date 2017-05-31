package com.system.manager;

import com.core.base.AbstractManager;
import com.core.constants.enums.AuthResourcesEnums;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.resourse.ResourcesListRequestVo;
import com.system.domain.request.resourse.ResourcesRequestVo;
import com.system.domain.response.resource.EditResourceResponseListVo;
import com.system.domain.response.resource.ResourcesDetailResponseVo;
import com.system.domain.response.resource.ResourcesListResponseVo;
import com.system.provider.client.SystemResourceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ResourceManager extends AbstractManager {

    @Autowired
    SystemResourceClient systemResourceClient;

    @Value("${service.rsa.privateKey}")
    private String rsaPrivateKey;

    /**
     * 用户列表 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public ResourcesListResponseVo list(ResourcesListRequestVo request) {
        return systemResourceClient.list(request);
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
    public void edit(ResourcesRequestVo request) {
        systemResourceClient.edit(request);
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

    public ResourcesDetailResponseVo detail(DefaultRequestVo request) {
        return systemResourceClient.detail(request);
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
    public void delete(DefaultRequestVo request) {
        systemResourceClient.delete(request);
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
    public ResourcesDetailResponseVo search(ResourcesRequestVo request) {
        return systemResourceClient.search(request);
    }


    /**
     * queryAdminResources:(编辑资源列表). <br/>
     *
     * @param resourcesRequestVo
     * @return
     * @author maven
     */
    public List<EditResourceResponseListVo> editList(ResourcesRequestVo resourcesRequestVo) {
        List<EditResourceResponseListVo> menus = new ArrayList<EditResourceResponseListVo>();
        List<ResourcesDetailResponseVo> resourcesDetailResponseVoList = systemResourceClient.editList(resourcesRequestVo);
        for (ResourcesDetailResponseVo resourcesDetailResponseVo : resourcesDetailResponseVoList) {
            // 取得子节点
            if (AuthResourcesEnums.LevelEnum.FIRST.getValue() == resourcesDetailResponseVo.getLevel()) {
                EditResourceResponseListVo subMenu = new EditResourceResponseListVo(resourcesDetailResponseVo.getResourceId(), resourcesDetailResponseVo.getUrl(), resourcesDetailResponseVo.getPage(), resourcesDetailResponseVo.getSort(), resourcesDetailResponseVo.getName());
                this.matchSubResource(subMenu, resourcesDetailResponseVoList);// 寻找子节点
                menus.add(subMenu);
            }
        }
        Collections.sort(menus);
        return menus;
    }

    /**
     * matchSubResource:(子节点封装). <br/>
     *
     * @param parent
     * @param editResourceResponseListVoList
     * @author maven
     */
    public final void matchSubResource(EditResourceResponseListVo parent, List<ResourcesDetailResponseVo> editResourceResponseListVoList) {
        List<EditResourceResponseListVo> subMenus = new ArrayList<EditResourceResponseListVo>();
        for (ResourcesDetailResponseVo resourcesDetailResponseVo : editResourceResponseListVoList) {
            if (parent.getResourceId().equals(resourcesDetailResponseVo.getParent())) {
                EditResourceResponseListVo subMenu = new EditResourceResponseListVo(resourcesDetailResponseVo.getResourceId(), resourcesDetailResponseVo.getUrl(), resourcesDetailResponseVo.getPage(), resourcesDetailResponseVo.getSort(), resourcesDetailResponseVo.getName());
                this.matchSubResource(subMenu, editResourceResponseListVoList);
                subMenus.add(subMenu);
            }
        }
        parent.setSubMenu(subMenus);
    }


}
