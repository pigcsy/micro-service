package com.system.domain.response.depart;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: AdminDepartResult <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author maven
 * @date: 2016年10月27日 下午4:15:31 <br/>
 * @since JDK 1.8
 */
@Data
public class DepartResultVo implements Serializable {


    private static final long serialVersionUID = 5650646564726276727L;
    private Integer id;

    private Integer departId;

    private String parent;

    private String name;

    private Byte type;

    private Integer departPersonNum;

    private String parentName;


}