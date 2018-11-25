package com.hoshiumi.mathumi.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.hoshiumi.mathumi.entity.QuestionEntity;
import com.hoshiumi.mathumi.entity.QuestionSetEntity;
import com.hoshiumi.mathumi.entity.UserEntity;

@Component
public interface QuestionMapper {
	
	@Insert("INSERT INTO question(authorid,createDate,content,knowledgeArea_List,ability_List)"+
            "VALUES(#{authorid},#{createDate},#{content},#{knowledgeArea_List},#{ability_List});")
    boolean createQuestionByTemplate(QuestionEntity ques);
	
	@Select("SELECT * FROM question_set WHERE id = #{id}")
    @Results({
        @Result(property = "id",  column = "id"),
        @Result(property = "setName", column = "setName"),
        @Result(property = "createDate", column = "createDate"),
        @Result(property = "question_list", column = "question_list"),
        @Result(property = "authorid", column = "authorid"),
        @Result(property = "gradeid", column = "gradeid")
    })
	QuestionSetEntity getQuestionSetById(Long id);
	
	@Select({"<script>",
        "SELECT *", 
        "FROM question",
        "WHERE id IN", 
          "<foreach item='item' index='index' collection='idGroup'",
            "open='(' separator=',' close=')'>",
            "#{item}",
          "</foreach>",
        "</script>"}) 
	@Results({
        @Result(property = "id",  column = "id"),
        @Result(property = "authorid", column = "authorid"),
        @Result(property = "createDate", column = "createDate"),
        @Result(property = "content", column = "content"),
        @Result(property = "knowledgeArea_List", column = "knowledgeArea_List"),
        @Result(property = "ability_List", column = "ability_List")
    })
	List<QuestionEntity> getQuestionsByList(@Param("idGroup") List<Long> idGroup);
}
