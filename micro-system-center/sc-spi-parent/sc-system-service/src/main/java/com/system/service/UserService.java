package com.system.service;

import com.system.domain.DefaultRequestDto;
import com.system.domain.request.user.UserListRequestDto;
import com.system.domain.request.user.UserRequestDto;
import com.system.domain.response.user.UserListResponseDto;
import com.system.domain.response.user.UserResult;
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
public interface UserService {

    /**
     * 用户列表 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public UserListResponseDto list(UserListRequestDto request);


    /**
     * 用户详情 detail:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public UserResult detail(DefaultRequestDto request);

    /**
     * 用户编辑或新增 editOrInsertUser:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void editOrInsertUser(UserRequestDto request);


    public boolean checkUser();

    /**
     * 用户限制 disable:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void disable(DefaultRequestDto request);


    /**
     * 根据用户名查询用户信息 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public UserResult queryByName(String code);
}
