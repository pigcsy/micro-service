package com.system.manager;

import com.core.base.AbstractManager;
import com.core.utils.AppUtils;
import com.core.utils.RSAUtils;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.external.ExternalListRequestVo;
import com.system.domain.request.external.ExternalRequestVo;
import com.system.domain.response.external.ExternalListResponseVo;
import com.system.domain.response.external.ExternalResult;
import com.system.provider.client.SystemExternalClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ExternalManager extends AbstractManager {

    @Autowired
    SystemExternalClient systemUserClient;

    @Value("${service.rsa.privateKey}")
    private String rsaPrivateKey;

    /**
     * 用户列表 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public ExternalListResponseVo list(ExternalListRequestVo request) {
        return systemUserClient.list(request);
    }

    /**
     * 用户详情 detail:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public ExternalResult detail(DefaultRequestVo request) {
        return systemUserClient.detail(request);
    }

    /**
     * 用户编辑或新增 editOrInsertUser:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void edit(ExternalRequestVo request) {
        boolean isAdd = request.getSystemId() == null ? true : false; // 是否是新增
        if (isAdd) {
            request.setCreateTime(new Timestamp(System.currentTimeMillis()));
        }
        // rsa私钥解密，md5加密
        if (StringUtils.isNotEmpty(request.getClientSecret())) {
            try {
                request.setClientSecret(RSAUtils.decryptByPrivateKey(request.getClientSecret(), rsaPrivateKey));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String md5Pwd = AppUtils.md5Encrypt(request.getClientSecret()); // md5 加密
            request.setClientSecret(md5Pwd);
        }
        request.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        systemUserClient.edit(request);
    }

    /**
     * 用户限制 userDisable:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void delete(DefaultRequestVo request) {
        systemUserClient.delete(request);
    }


}
