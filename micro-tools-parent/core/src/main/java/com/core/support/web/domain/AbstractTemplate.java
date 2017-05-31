package com.core.support.web.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author 轴承
 * @date 2017/4/13 下午4:19
 */
@Data
public abstract class AbstractTemplate<T> implements Serializable {
    private static final long serialVersionUID = -2371755377799303507L;
    private Map<String, T> template;

    /**
     * 推配置
     *
     * @param config
     * @return
     */
    public Map<String, T> putConfig(String code, T config) {
        if (this.getTemplate() == null) {
            this.template = new HashMap<>();
        }
        this.getTemplate().put(code, config);
        return this.getTemplate();
    }

    /**
     * 根据code获取配置
     *
     * @param code
     * @return
     */
    public T getConfig(String code) {
        if (this.getTemplate() == null) {
            return null;
        }
        return this.getTemplate().get(code);
    }
}
