package com.core.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Comparable<Menu>, Serializable {

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

    public Menu() {
    }

    public Menu(int resourceId, String url, String page, String nm, short sort, int level, int parent, String icon, String code) {
        this.resourceId = resourceId;
        this.url = url;
        this.nm = nm;
        this.page = page;
        this.sort = sort;
        this.level = level;
        this.parent = parent;
        this.icon = icon;
        this.code = code;
    }

    public Menu(int resourceId, String url, String page, String nm, short sort, int level, int parent, String icon) {
        this.resourceId = resourceId;
        this.url = url;
        this.nm = nm;
        this.page = page;
        this.sort = sort;
        this.level = level;
        this.parent = parent;
        this.icon = icon;
    }

    public Menu(int resourceId, String url, String page, String nm, short sort, int level, int parent) {
        this.resourceId = resourceId;
        this.url = url;
        this.nm = nm;
        this.page = page;
        this.sort = sort;
        this.level = level;
        this.parent = parent;
    }


    @Override
    public int compareTo(Menu dto) {
        if (this.getSort() > dto.getSort()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        return getResourceId() == menu.getResourceId();

    }

    @Override
    public int hashCode() {
        return getResourceId();
    }
}
