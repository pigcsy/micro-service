package com.cache.support.vo.redis.resource;

import lombok.Data;

import java.util.List;

@Data
public class MetaDataSource {
    private String role;
    private Integer systemType;
    private List<String> urls;//

    public MetaDataSource(String role, Integer systemType, List<String> urls) {
        super();
        this.role = role;
        this.systemType = systemType;
        this.urls = urls;
    }

    public MetaDataSource() {
        super();
    }

    public MetaDataSource(String role, List<String> urls) {
        super();
        this.role = role;
        this.urls = urls;
    }

}
