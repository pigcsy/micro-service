package com.core.support.web.domain;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author 轴承
 * @date 2016/10/26 上午11:29
 */
public abstract class BaseListResponse<T> extends BaseResponse {
    private static final long serialVersionUID = 5963249852889278585L;
    protected List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
