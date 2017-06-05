package com.system.domain;

import com.core.support.web.domain.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class DefaultRequestVo extends BaseRequest {

    private Integer entityId;


}
