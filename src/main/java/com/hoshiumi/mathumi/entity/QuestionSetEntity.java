package com.hoshiumi.mathumi.entity;

import java.io.Serializable;
import java.util.Date;

public class QuestionSetEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long authorid;
	private Date createDate;
	private String setName;
	private String question_list;
	private Long gradeid;
	
	public QuestionSetEntity() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuthorid() {
		return authorid;
	}

	public void setAuthorid(Long authorid) {
		this.authorid = authorid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public String getQuestion_list() {
		return question_list;
	}

	public void setQuestion_list(String question_list) {
		this.question_list = question_list;
	}

	public Long getGradeid() {
		return gradeid;
	}

	public void setGradeid(Long gradeid) {
		this.gradeid = gradeid;
	}
	
	
}
