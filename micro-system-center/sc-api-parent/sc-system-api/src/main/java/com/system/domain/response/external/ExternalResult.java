package com.system.domain.response.external;/**
 * Created by maven on 2017/3/9.
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @author Maven
 *         <pre>
 *                                                             _oo0oo_
 *                                                            o8888888o
 *                                                            88" . "88
 *                                                            (| -_- |)
 *                                                            0\  =  /0
 *                                                          ___/`---'\___
 *                                                        .' \\|     |// '.
 *                                                       / \\|||  :  |||// \
 *                                                      / _||||| -:- |||||- \
 *                                                     |   | \\\  -  /// |   |
 *                                                     | \_|  ''\---/''  |_/ |
 *                                                     \  .-\__  '-'  ___/-. /
 *                                                   ___'. .'  /--.--\  `. .'___
 *                                                ."" '<  `.___\_<|>_/___.' >' "".
 *                                               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *                                               \  \ `_.   \_ __\ /__ _/   .-` /  /
 *                                           =====`-.____`.___ \_____/___.-`___.-'=====
 *                                                             `=---='
 *                                           ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *                                                     佛祖开光         永无BUG
 *                                         </pre>
 */
@Data
public class ExternalResult implements Serializable {
    private static final long serialVersionUID = 667365946500074309L;
    private Integer systemId;
    private String systemName;
    private Integer systemType;
    private String clientId;
    private String systemRemark;
    private String clientSecret;
    private String scope;
    private Double accessTokenValidity;
    private Double refreshTokenValidity;
}
