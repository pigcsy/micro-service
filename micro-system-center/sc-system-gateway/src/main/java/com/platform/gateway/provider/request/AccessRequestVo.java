package com.platform.gateway.provider.request;

import com.core.support.web.domain.BaseRequest;
import lombok.Data;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/3/28 下午3:32
 */
@Data
public class AccessRequestVo extends BaseRequest {
    private static final long serialVersionUID = -3828196089397024585L;
    private String gateway;
    private String customerId;
    private Integer systemCode;
    private Long minLimitNum;
    private Long dailyLimitNum;
}
