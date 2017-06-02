package com.system.domain.request.external;/**
 * Created by maven on 2017/3/9.
 */

import com.core.support.web.domain.BasePageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户VO
 *
 * @author Maven
 *         <pre>
 *                                                                                     _oo0oo_
 *                                                                                    o8888888o
 *                                                                                    88" . "88
 *                                                                                    (| -_- |)
 *                                                                                    0\  =  /0
 *                                                                                  ___/`---'\___
 *                                                                                .' \\|     |// '.
 *                                                                               / \\|||  :  |||// \
 *                                                                              / _||||| -:- |||||- \
 *                                                                             |   | \\\  -  /// |   |
 *                                                                             | \_|  ''\---/''  |_/ |
 *                                                                             \  .-\__  '-'  ___/-. /
 *                                                                           ___'. .'  /--.--\  `. .'___
 *                                                                        ."" '<  `.___\_<|>_/___.' >' "".
 *                                                                       | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *                                                                       \  \ `_.   \_ __\ /__ _/   .-` /  /
 *                                                                   =====`-.____`.___ \_____/___.-`___.-'=====
 *                                                                                     `=---='
 *                                                                   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *                                                                             佛祖开光         永无BUG
 *                                                                 </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalListRequestDto extends BasePageRequest {
    private Integer systemId;

    public ExternalListRequestDto(int currentPage, int pageSize) {
        super(currentPage, pageSize);
    }

}
