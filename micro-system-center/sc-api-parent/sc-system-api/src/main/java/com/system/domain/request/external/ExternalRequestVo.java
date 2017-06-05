package com.system.domain.request.external;

import com.core.support.web.domain.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * package com.system.domain.request.external;
 * <p>
 * import com.core.support.web.domain.BaseRequest;
 * import lombok.Data;
 * <p>
 * <p>
 * /**
 * ClassName: AdminStaffRequest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author maven
 * @since JDK 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExternalRequestVo extends BaseRequest {
    private Integer systemId;
    private String systemName;
    private Integer systemType;
    private String clientId;
    private String systemRemark;
    private String clientSecret;
    private String scope;
    private Double accessTokenValidity;
    private Double refreshTokenValidity;
    private Date createTime;
    private Date updateTime;
}
