package com.core.domain;

import java.io.Serializable;

/**
 * ClassName: Role <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author
 * @date: 2016年10月20日 下午1:13:21 <br/>
 * @since JDK 1.8
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 642538304346363259L;
    private String code;
    private String name;
    private String pattern;

    public Role(String code, String name, String pattern) {
        this.code = code;
        this.name = name;
        this.pattern = pattern;
    }

    public Role(String code, String pattern) {
        this.code = code;
        this.pattern = pattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "Role [code=" + code + ", pattern=" + pattern + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return getPattern() != null ? getPattern().equals(role.getPattern()) : role.getPattern() == null;

    }

    @Override
    public int hashCode() {
        return getPattern() != null ? getPattern().hashCode() : 0;
    }
}
