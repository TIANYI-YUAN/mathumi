package com.hoshiumi.mathumi.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InvitationRecordEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long invitation_entityId;
	private Long userid;
	private String timeid;
	private Date invitation_date;
    
	public InvitationRecordEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTimeid() {
		return timeid;
	}

	public void setTimeid(String timeid) {
		this.timeid = timeid;
	}

	public Date getInvitation_date() {
		return invitation_date;
	}

	public void setInvitation_date(Date invitation_date) {
		this.invitation_date = invitation_date;
		DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		String strDate = dateFormat.format(invitation_date);
		this.timeid = strDate;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getInvitation_entityId() {
		return invitation_entityId;
	}

	public void setInvitation_entityId(Long invitation_entityId) {
		this.invitation_entityId = invitation_entityId;
	}

	

   

}