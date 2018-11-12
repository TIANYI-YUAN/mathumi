package com.hoshiumi.mathumi.entity;

import java.io.Serializable;
import java.util.Date;


public class MobileVertEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String mobile;
	private String vert_code;
	private Date expiry_time;
	
    
	public MobileVertEntity() {
		super();
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVert_code() {
		return vert_code;
	}

	public void setVert_code(String vert_code) {
		this.vert_code = vert_code;
	}

	public Date getExpiry_time() {
		return expiry_time;
	}

	public void setExpiry_time(Date expiry_time) {
		this.expiry_time = expiry_time;
	}

   

}