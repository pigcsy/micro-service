package com.system.domain.response.oath;/**
 * Created by maven on 2017/3/9.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Maven
 *         <pre>
 *                                                                                             _oo0oo_
 *                                                                                            o8888888o
 *                                                                                            88" . "88
 *                                                                                            (| -_- |)
 *                                                                                            0\  =  /0
 *                                                                                          ___/`---'\___
 *                                                                                        .' \\|     |// '.
 *                                                                                       / \\|||  :  |||// \
 *                                                                                      / _||||| -:- |||||- \
 *                                                                                     |   | \\\  -  /// |   |
 *                                                                                     | \_|  ''\---/''  |_/ |
 *                                                                                     \  .-\__  '-'  ___/-. /
 *                                                                                   ___'. .'  /--.--\  `. .'___
 *                                                                                ."" '<  `.___\_<|>_/___.' >' "".
 *                                                                               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *                                                                               \  \ `_.   \_ __\ /__ _/   .-` /  /
 *                                                                           =====`-.____`.___ \_____/___.-`___.-'=====
 *                                                                                             `=---='
 *                                                                           ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *                                                                                     佛祖开光         永无BUG
 *                                                                         </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OauthSystemResponseDto implements Serializable {
    private Integer systemId;
    private String systemName;
    private String systemRemark;
    private String clientId;
    private Integer systemType;
    private Integer dailyAccessNum;
    private Integer minuteAccessNum;
}
