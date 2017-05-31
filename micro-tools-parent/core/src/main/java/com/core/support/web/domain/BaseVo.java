package com.core.support.web.domain;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * ${DESCRIPTION}
 *
 * @author 轴承
 * @date 2017/3/22 上午10:28
 */
public class BaseVo implements Serializable {
    private static final long serialVersionUID = -433840516531077797L;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
