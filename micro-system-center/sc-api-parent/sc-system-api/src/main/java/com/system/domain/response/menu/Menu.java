package com.system.domain.response.menu;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int resourceId;
    private String url;
    private String nm;
    private String page;
    private List<Menu> subMenu;
    private short sort;
    private int level;
    private int parent;
    private String icon;
    private String code;


}
