package com.hoshiumi.mathumi.controller;

import java.io.IOException;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoshiumi.mathumi.entity.UserEntity;
import com.hoshiumi.mathumi.lib.HoshiUmiLib;
import com.hoshiumi.mathumi.mapper.*;

@Controller
public class AuthorizeController {

	
	@Autowired
	HoshiUmiLib HoshiUmiLib;
	
	@Autowired
	AuthorizeMapper AuthorizeMapper;
	
	public AuthorizeController() {
		
	}

	
	@RequestMapping(value="/rpc/authorize",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8080")
    public Map<String,String> function(@RequestParam(name="methodId", required=true)int functionid,@RequestParam(name="postData", required=true)String other) throws JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper(); 
		TypeReference<HashMap<String,String>> typeRef = new TypeReference<HashMap<String,String>>() {};
		
		switch(functionid){
			case 1://check login password
				Map<String,String> user = mapper.readValue(other, typeRef);
				return checkLogin(user);
			default:
				return test3();
				
		
		}

		
    }

	public Map<String,String> checkLogin(Map<String,String> other) {
		Map<String,String> rs = new HashMap<String,String>();
		UserEntity user = AuthorizeMapper.getOneByUsername(other.get("username"));
		
		if(user == null) {
			rs.put("response", "no such username");
			return rs;
		}
		
		if(user.getPassword_salthash().equals(other.get("password")))
		{
			AuthorizeMapper.updateLogincookie(HoshiUmiLib.crypt(HoshiUmiLib.cookieGenerate(user.getUsername(),user.getPassword_salthash())),user.getUserid());
			rs.put("username", user.getUsername());
			rs.put("authorizeToken", AuthorizeMapper.getLoginCookieByUsername(user.getUsername()));
		}else {
			rs.put("response", "password error");
		}

		return rs;
	}
	
	public Map<String,String> compareCookie(String username,String cookie){
		Map<String,String> rs = new HashMap<String,String>();
		if(AuthorizeMapper.getLoginCookieByUsername(username).equals(cookie)) {
			rs.put("response", "ok");
		}else {
			rs.put("response", "error");
		}
		return rs;
	}
	
	public Map<String,String> test3() {
		Map<String,String> rs = new HashMap<String,String>();
		
		rs.put("response", "test ok3!");
		
		return rs;
	}
	
}
