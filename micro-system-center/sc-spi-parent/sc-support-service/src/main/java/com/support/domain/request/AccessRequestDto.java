package com.support.domain.request;

import com.core.support.web.domain.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/4/21 上午9:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccessRequestDto extends BaseRequest {
    private static final long serialVersionUID = -8408111140226606516L;
    private String gateway;
    private String customerId;
    private Integer systemCode;
    private Long minLimitNum;
    private Long dailyLimitNum;
}
