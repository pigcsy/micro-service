package com.safty.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="safe_base_data")
public class SaftyBaseData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4105133117903660124L;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name="ip_address")
	private String ipAddress;
	
	@Column(name="occur_time")
	private Date occurTime;
	
	@Column(name="occur_day")
	private String occurDay;
	
	@Column(name="response_time")
	private BigDecimal responseTime;
	
	@Column(name="req_url")
	private String reqUrl;
	
	@Column(name="mode")
	private String mode;
	
	@Column(name="resp_state")
	private String respState;
	
	@Column(name="params")
	private String params;
	
	@Column(name="system_id")
	private Integer systemId;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;

	
	public SaftyBaseData() {
		super();
	}


	public SaftyBaseData(String ipAddress, Date occurTime, String occurDay, BigDecimal responseTime, String reqUrl,
			String mode, String respState, String params) {
		super();
		this.ipAddress = ipAddress;
		this.occurTime = occurTime;
		this.occurDay = occurDay;
		this.responseTime = responseTime;
		this.reqUrl = reqUrl;
		this.mode = mode;
		this.respState = respState;
		this.params = params;
	}
	
	
}
