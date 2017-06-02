package com.safty.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "black_white_data")
public class BlackWhiteData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4105133117903660124L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "url")
    private String url;

    @Column(name = "type")
    private Integer type;

    @Column(name = "remark")
    private String remark;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public BlackWhiteData(String ipAddress, String url, Integer type) {
        super();
        this.ipAddress = ipAddress;
        this.url = url;
        this.type = type;
    }

    public BlackWhiteData() {
        super();
    }


}
