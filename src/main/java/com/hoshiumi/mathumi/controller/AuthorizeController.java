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


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoshiumi.mathumi.entity.UserEntity;
import com.hoshiumi.mathumi.lib.HoshiUmiLib;
import com.hoshiumi.mathumi.mapper.*;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Controller
public class AuthorizeController {

	
	@Autowired
	HoshiUmiLib HoshiUmiLib;
	
	@Autowired
	AuthorizeMapper AuthorizeMapper;
	
	//Twilio SMS
	public static final String ACCOUNT_SID =
            "ACc6300e4fbca359a3b90553b3570e6d96";
    public static final String AUTH_TOKEN =
            "a8aaeabf80d1c474ddb0afa254899c0b";
	
	
	
	
	public AuthorizeController() {
		
	}
	


	
	
	
	@RequestMapping(value="/rpc/authorize",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8080")
    public Map<String,String> function(@RequestParam(name="methodId", required=true)int functionid,@RequestParam(name="postData", required=true)String other) throws JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper(); 
		TypeReference<HashMap<String,String>> typeRef = new TypeReference<HashMap<String,String>>() {};
		Map<String,String> temp = new HashMap<String,String>();
		
		
		switch(functionid){
			case 1://check login password
				temp = mapper.readValue(other, typeRef);
				return checkLogin(temp);
			case 2://check token
				temp = mapper.readValue(other, typeRef);
				return compareCookie(temp.get("username"),temp.get("authorizeToken"));
			case 3://generate SMS vertification code
				temp = mapper.readValue(other, typeRef);
				return genSmsVert(temp.get("mobile"));
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
		
		if(AuthorizeMapper.getLoginCookieByUsername(username)==null) {
			rs.put("response", "no such username");
		}else if(AuthorizeMapper.getLoginCookieByUsername(username).equals(cookie)) {
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
	
	public Map<String,String> genSmsVert(String mobile) {
		Map<String,String> rs = new HashMap<String,String>();
		
		String vert_code = String.valueOf((int)((Math.random()*9+1)*1000));
		long currentTime = System.currentTimeMillis();
		currentTime +=30*60*1000;
		Date expiry_time = new Date(currentTime);
		
		
		if(AuthorizeMapper.checkExistSMSvertification(mobile)==null) {
		
			AuthorizeMapper.createMobileVertInstance(mobile, vert_code, expiry_time);
		}else {
			AuthorizeMapper.updateExistSMSvertification(mobile, vert_code, expiry_time);

		}
		
		
		rs = sendSMS(mobile,vert_code);
		
		
		
		return rs;
	}
	
	public Map<String, String> sendSMS(String mobile,String code) {
		Map<String,String> rs = new HashMap<String,String>();
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+61"+mobile), // to
                        new PhoneNumber("+18303680307"), // from
                        "mathumi test,your code:"+code)
                .create();
			
		rs.put("SID", message.getSid());
		return rs;
	}
}
