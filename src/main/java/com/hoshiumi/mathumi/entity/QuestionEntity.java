package com.hoshiumi.mathumi.entity;

import java.io.Serializable;
import java.util.Date;

public class QuestionEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long authorid;
	private Date createDate;
	private String content;
	private String knowledgeArea_List;
	private String ability_List;
	
	
	public QuestionEntity() {
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


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getKnowledgeArea_List() {
		return knowledgeArea_List;
	}


	public void setKnowledgeArea_List(String knowledgeArea_List) {
		this.knowledgeArea_List = knowledgeArea_List;
	}


	public String getAbility_List() {
		return ability_List;
	}


	public void setAbility_List(String ability_List) {
		this.ability_List = ability_List;
	}
}
