package com.system.manager;

import com.core.base.AbstractManager;
import com.system.domain.response.menu.MenuResponseVo;
import com.system.provider.client.SystemMenuClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuManager extends AbstractManager {

    @Autowired
    SystemMenuClient systemMenuClient;


    public MenuResponseVo menu() {
        return systemMenuClient.menu();
    }
}
