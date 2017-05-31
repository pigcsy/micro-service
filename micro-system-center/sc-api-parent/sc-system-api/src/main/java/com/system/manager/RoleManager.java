package com.system.manager;

import com.core.base.AbstractManager;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.role.RoleListRequestVo;
import com.system.domain.request.role.RoleRequestVo;
import com.system.domain.response.role.RoleListResponseVo;
import com.system.domain.response.role.RoleResourcesResponseVo;
import com.system.provider.client.SystemRoleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RoleManager extends AbstractManager {

    @Autowired
    SystemRoleClient systemRoleClient;

    @Value("${service.rsa.privateKey}")
    private String rsaPrivateKey;

    /**
     * 角色列表
     *
     * @param request
     * @return
     */
    public RoleListResponseVo list(RoleListRequestVo request) {
        System.out.println("aa");
        return systemRoleClient.list(request);
    }

    /**
     * @param request
     * @return
     */
    public void edit(RoleRequestVo request) {
        systemRoleClient.edit(request);
    }


    /**
     * @param request
     */
    public void delete(DefaultRequestVo request) {
        systemRoleClient.delete(request);
    }

    /**
     * @param request
     * @return
     */
    public RoleResourcesResponseVo resourceList(DefaultRequestVo request) {
        return systemRoleClient.resourceList(request);
    }

    /**
     * @param request
     */
    public void resourceEdit(RoleListRequestVo request) {
        systemRoleClient.resourceEdit(request);
    }

}
