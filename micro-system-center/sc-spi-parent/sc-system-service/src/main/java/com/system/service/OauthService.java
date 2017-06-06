package com.system.service;

import com.cache.support.vo.redis.resource.MetaDataSource;
import com.system.domain.response.oath.OauthSystemResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

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
public interface OauthService {

    /**
     *
     */

    public OauthSystemResponseDto queryClientDetailsById(String clientId);

    public List<String> queryRoleByUserId(Integer userId);

    public List<MetaDataSource> queryAllResourcesByRole();

}
