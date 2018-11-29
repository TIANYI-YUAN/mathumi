package com.hoshiumi.mathumi.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoshiumi.mathumi.entity.InvitationRecordEntity;
import com.hoshiumi.mathumi.entity.MobileVertEntity;
import com.hoshiumi.mathumi.entity.QuestionEntity;
import com.hoshiumi.mathumi.entity.QuestionSetEntity;
import com.hoshiumi.mathumi.entity.UserEntity;
import com.hoshiumi.mathumi.lib.HoshiUmiLib;
import com.hoshiumi.mathumi.mapper.*;
import com.hoshiumi.mathumi.util.*;

@Controller
public class QuestionController {

	@Autowired
	HoshiUmiLib HoshiUmiLib;
	
	@Autowired
	QuestionMapper QuestionMapper;
	
	public QuestionController() {
		
	}
	
	@RequestMapping(value="/rpc/question",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin//(origins = "http://localhost:8080")
    public Map<String,String> function(@RequestParam(name="methodId", required=true)int functionid,
    		@RequestParam(name="postData", required=true)String other) 
    				throws JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper(); 
		TypeReference<HashMap<String,String>> typeRef = new TypeReference<HashMap<String,String>>() {};
		Map<String,String> temp = new HashMap<String,String>();
		
		
		switch(functionid){
			case 1:
				temp = mapper.readValue(other, typeRef);
				return createQuestionByTemplate(temp);
			case 2:
				temp = mapper.readValue(other, typeRef);
				return getFullQuestionSetById(temp);
			default:
				return test3();
				
		
		}

		
    }
	
	public Map<String,String> createQuestionByTemplate(Map<String,String> other) {
		Map<String,String> rs = new HashMap<String,String>();
		
		QuestionEntity question = new QuestionEntity();
		question.setAuthorid(Long.parseLong(other.get("authorid")));
		question.setCreateDate(new Date());
		question.setContent(other.get("template"));
		question.setKnowledgeArea_List(other.get("knowledgeArea"));
		question.setAbility_List(other.get("ability"));
		 
		if(QuestionMapper.createQuestionByTemplate(question)) {
			rs.put("response", "ok");
		}else {
			rs.put("response", "db error");
		}
		
		
		return rs;
	}
	
	
	public Map<String,String> test3() {
		Map<String,String> rs = new HashMap<String,String>();
		int j = 0;
		try {
            j++;
            rs.put("response", String.valueOf(j));
        } catch (Exception e) {
            e.printStackTrace(); 
        }
		
		
		return rs;
	}
	
	public Map<String,String> getFullQuestionSetById(Map<String,String> data) throws IOException{
		Map<String,String> rs = new HashMap<String,String>();
		Long id = Long.parseLong(data.get("setId"));
		
		
		//get questionsetEntity
		QuestionSetEntity qs = QuestionMapper.getQuestionSetById(id);
		
		Map<String,String> qsMap = new HashMap<String,String>();
		qsMap.put("setName", qs.getSetName());
		qsMap.put("questionList",qs.getQuestion_list());
		qsMap.put("gradeId", qs.getGradeid().toString());
		
		String json1 = new ObjectMapper().writeValueAsString(qsMap);
		
		rs.put("questionSet", json1);
		
		//get Questions
		
		List<Long> qsli = new ObjectMapper().readValue(qs.getQuestion_list(), new TypeReference<List<Long>>(){});
		
		
		List<QuestionEntity> qeli = QuestionMapper.getQuestionsByList(qsli);
		
		String json2 = new ObjectMapper().writeValueAsString(qeli);
		rs.put("questions", json2);
		return rs;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
