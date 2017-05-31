package com.core.support.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author 轴承
 * @date 2017/3/31 下午1:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Snipplet implements Serializable {
    private static final long serialVersionUID = -8495993173415896896L;
    private String field;
    private List<String> snipplets;
}
