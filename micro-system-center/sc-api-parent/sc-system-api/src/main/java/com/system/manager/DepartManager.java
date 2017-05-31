package com.system.manager;

import com.core.base.AbstractManager;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.depart.DepartListRequestVo;
import com.system.domain.request.depart.DepartRequestVo;
import com.system.domain.response.depart.DepartListResponseVo;
import com.system.domain.response.depart.DepartResultVo;
import com.system.provider.client.SystemDepartClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartManager extends AbstractManager {

    @Autowired
    SystemDepartClient systemDepartClient;


    /**
     * 部门列表 list
     *
     * @author maven
     */
    public DepartListResponseVo list(DepartListRequestVo request) {
        return systemDepartClient.list(request);
    }

    /**
     * 部门编辑
     * editGoods:(这里用一句话描述这个方法的作用). <br/>
     *
     * @param request
     * @return
     * @author Maven
     * @date: 2016年11月10日 下午5:37:08
     */
    public void edit(DepartRequestVo request) {
        systemDepartClient.edit(request);
    }

    /**
     * 部门详情
     * editGoods:(这里用一句话描述这个方法的作用). <br/>
     *
     * @param request
     * @return
     * @author Maven
     * @date: 2016年11月10日 下午5:37:08
     */

    public DepartResultVo detail(DefaultRequestVo request) {
        return systemDepartClient.detail(request);
    }

    /**
     * 删除部门
     * editGoods:(这里用一句话描述这个方法的作用). <br/>
     *
     * @param request
     * @return
     * @author Maven
     * @date: 2016年11月10日 下午5:37:08
     */
    public void delete(DefaultRequestVo request) {
        systemDepartClient.delete(request);
    }

    /**
     * 部门编辑列表 list
     *
     * @author maven
     */
    public List<DepartResultVo> editList(DepartRequestVo request) {
        return systemDepartClient.editList(request);
    }
}
