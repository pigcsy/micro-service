package com.system.domain.response.user;/**
 * Created by maven on 2017/3/9.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Maven
 *         <pre>
 *                                                                             _oo0oo_
 *                                                                            o8888888o
 *                                                                            88" . "88
 *                                                                            (| -_- |)
 *                                                                            0\  =  /0
 *                                                                          ___/`---'\___
 *                                                                        .' \\|     |// '.
 *                                                                       / \\|||  :  |||// \
 *                                                                      / _||||| -:- |||||- \
 *                                                                     |   | \\\  -  /// |   |
 *                                                                     | \_|  ''\---/''  |_/ |
 *                                                                     \  .-\__  '-'  ___/-. /
 *                                                                   ___'. .'  /--.--\  `. .'___
 *                                                                ."" '<  `.___\_<|>_/___.' >' "".
 *                                                               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *                                                               \  \ `_.   \_ __\ /__ _/   .-` /  /
 *                                                           =====`-.____`.___ \_____/___.-`___.-'=====
 *                                                                             `=---='
 *                                                           ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *                                                                     佛祖开光         永无BUG
 *                                                         </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResult implements Serializable {
    private static final long serialVersionUID = 8783844600728808785L;
    private Integer staffId;
    private String code;
    private String pwd;
    private Integer departId;
    private Timestamp insTm;
    private Byte leader;
    private Byte status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String staffRoster;
    private String staffName;
    private String staffPost;
    private String staffPhone;
    private String staffEmail;
    private String departName;
    private Integer systemId;
    private Integer dailyAccessNum;
    private Integer minuteAccessNum;


}
