package com.hoshiumi.mathumi.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.hoshiumi.mathumi.entity.QuestionEntity;

@Component
public interface QuestionMapper {
	
	@Insert("INSERT INTO question(authorid,createDate,content,knowledgeArea_List,ability_List)"+
            "VALUES(#{authorid},#{createDate},#{content},#{knowledgeArea_List},#{ability_List});")
    boolean createQuestionByTemplate(QuestionEntity ques);
}
