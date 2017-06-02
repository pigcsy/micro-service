package com.platform.gateway.filter;

public enum ZuulTypeEn {
    pre("pre"), routing("routing"), post("post"), error("error");

    private String mean;

    private ZuulTypeEn(String mean) {
        this.mean = mean;
    }

    public String getMean() {
        return mean;
    }

}
