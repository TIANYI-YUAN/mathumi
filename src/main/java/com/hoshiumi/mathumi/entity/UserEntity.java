package com.hoshiumi.mathumi.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long userid;
	private String username;
	private String password_salthash;
	private String firstname;
	private String lastname;
    private String phone;
    private String address;
    private String logincookie;
    
	public UserEntity() {
		super();
	}

	public UserEntity(String username, String password_salthash,
                      String firstname,String lastname,String phone,String address,String logincookie) {
		super();
        this.username = username;
		this.password_salthash = password_salthash;
		this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.logincookie = logincookie;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword_salthash() {
		return password_salthash;
	}

	public void setPassword_salthash(String password_salthash) {
		this.password_salthash = password_salthash;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

    public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getLogincookie() {
		return logincookie;
	}

	public void setLogincookie(String logincookie) {
		this.logincookie = logincookie;
	}


	@Override
	public String toString() {
	
		return "userName " + this.username + ", pasword " + this.password_salthash ;
	}

}