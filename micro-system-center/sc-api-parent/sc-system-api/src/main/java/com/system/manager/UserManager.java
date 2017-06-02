package com.system.manager;

import com.core.base.AbstractManager;
import com.core.exception.LogicException;
import com.core.utils.AppUtils;
import com.core.utils.RSAUtils;
import com.system.domain.DefaultRequestVo;
import com.system.domain.request.user.UserListRequestVo;
import com.system.domain.request.user.UserRequestVo;
import com.system.domain.response.user.UserListResponseVo;
import com.system.domain.response.user.UserResult;
import com.system.provider.client.SystemUserClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.util.Date;

@Component
public class UserManager extends AbstractManager {

    @Autowired
    SystemUserClient systemUserClient;

    @Value("${service.rsa.privateKey}")
    private String rsaPrivateKey;

    /**
     * 用户列表 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public UserListResponseVo list(UserListRequestVo request) {
        return systemUserClient.list(request);
    }

    /**
     * 用户详情 detail:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public UserResult detail(DefaultRequestVo request) {
        return systemUserClient.detail(request);
    }

    /**
     * 用户编辑或新增 editOrInsertUser:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void editOrInsertUser(UserRequestVo request) throws Exception {
        boolean isAdd = request.getStaffId() == null ? true : false; // 是否是新增
        if (isAdd) {
            request.setInsTm(new Date());
        }
        if (request.getStaffId() == null) {
            throw new LogicException("员工工号不能为空");
        }
        if (request.getPwd() == null) {
            throw new LogicException("密码不能为空");
        }
        // rsa私钥解密，md5加密
        if (StringUtils.isNotEmpty(request.getPwd())) {
            String enCodePass = URLDecoder.decode(request.getPwd().toString(), "UTF-8");
            request.setPwd(RSAUtils.decryptByPrivateKey(enCodePass, rsaPrivateKey));
            String md5Pwd = AppUtils.md5Encrypt(request.getPwd()); // md5 加密
            request.setPwd(md5Pwd);
        }
        systemUserClient.editOrInsertUser(request);
    }

    /**
     * 用户限制 userDisable:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void userDisable(DefaultRequestVo request) {
        systemUserClient.userDisable(request);
    }


}
