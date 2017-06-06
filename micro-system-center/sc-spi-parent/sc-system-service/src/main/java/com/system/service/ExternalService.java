package com.system.service;


import com.system.domain.DefaultRequestDto;
import com.system.domain.request.external.ExternalListRequestDto;
import com.system.domain.request.external.ExternalRequestDto;
import com.system.domain.response.external.ExternalListResponseDto;
import com.system.domain.response.external.ExternalResult;
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
public interface ExternalService {


    /**
     * 用户列表 list:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public ExternalListResponseDto list(ExternalListRequestDto request);


    /**
     * 用户详情 detail:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public ExternalResult detail(DefaultRequestDto request);

    /**
     * 用户编辑或新增 editOrInsertUser:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void edit(ExternalRequestDto request);


    /**
     * 用户限制 disable:(这里用一句话描述这个方法的作用). <br/>
     *
     * @author maven
     */
    public void delete(DefaultRequestDto request);

}
