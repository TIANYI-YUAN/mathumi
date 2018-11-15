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
import com.hoshiumi.mathumi.entity.InvitationRecordEntity;
import com.hoshiumi.mathumi.entity.MobileVertEntity;
import com.hoshiumi.mathumi.entity.UserEntity;
import com.hoshiumi.mathumi.lib.HoshiUmiLib;
import com.hoshiumi.mathumi.mapper.*;
import com.hoshiumi.mathumi.util.*;


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
    @CrossOrigin//(origins = "http://localhost:8080")
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
			case 4://create new user via sign up form
				temp = mapper.readValue(other, typeRef);
				return signupNewUser(temp);
			case 5://get user info
				temp = mapper.readValue(other, typeRef);
				return getUserInfoByToken(temp);
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
		
		if(BCrypt.checkpw(other.get("password"),user.getPassword_salthash()))
		{
			AuthorizeMapper.updateLogincookie(HoshiUmiLib.crypt(HoshiUmiLib.cookieGenerate(user.getUsername(),user.getPassword_salthash())),user.getUserid());
			rs.put("username", user.getUsername());
			rs.put("name", user.getFirstname()+" "+user.getLastname());
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
			rs.put("response", "compareCookie error");
		}
		return rs;
	}
	
	public Map<String,String> test3() {
		Map<String,String> rs = new HashMap<String,String>();
		
		rs.put("response", "test ok3!");
		
		return rs;
	}
	
	public Map<String,String> genSmsVert(String mobile) {
		System.out.println("genSmsVert");
		Map<String,String> rs = new HashMap<String,String>();
		mobile = mobile.trim();
		if(mobile.equals("")) {
			return null;
		}
		
		//remove the first 0 of australia mobile number
		if(mobile.substring(0,1).equals("0")) {
			mobile = mobile.substring(1);
		}
		
		
		String vert_code = String.valueOf((int)((Math.random()*9+1)*1000));
		long currentTime = System.currentTimeMillis();
		currentTime +=30*60*1000;
		Date expiry_time = new Date(currentTime);
		
		MobileVertEntity me = AuthorizeMapper.getExistSMSverification(mobile);
		
		if(me==null) {
			// do mobile validation here  ----->
			AuthorizeMapper.createMobileVertInstance(mobile, vert_code, expiry_time);
		}else {
			AuthorizeMapper.updateExistSMSverification(mobile, vert_code, expiry_time);

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
	
	
	public Map<String, String> signupNewUser(Map<String, String> signup) {
		Map<String,String> rs = new HashMap<String,String>();
		
		String mobile = signup.get("mobile").trim();
		if(mobile.equals("")) {
			return null;
		}
		//remove the first 0 of australia mobile number
		if(mobile.substring(0,1).equals("0")) {
			mobile = mobile.substring(1);
		}
		
		MobileVertEntity me = AuthorizeMapper.getExistSMSverification(mobile);
		
		if(AuthorizeMapper.getOneByUsername(signup.get("username"))!=null) {
			rs.put("response_code", "4");
			rs.put("response_text", "duplicate username");
			return rs;
		}
		
		if(me==null) {
			rs.put("response_code", "1");
			rs.put("response_text", "no such mobile number");
			return rs;
		}
		
		if(!me.getVert_code().equalsIgnoreCase(signup.get("vertification").trim())) {
			rs.put("response_code", "2");
			rs.put("response_text", "vertification code not correct");
			return rs;
		}
		
		String invitationEntityId = null;
		if(!signup.get("invitation").trim().equals("")) {
			invitationEntityId = AuthorizeMapper.getInvitationEntityIdByInvitationCode(signup.get("invitation").trim());
			if(invitationEntityId==null) {
				rs.put("response_code", "3");
				rs.put("response_text", "no such invitation code");
				return rs;
			}
			
		}
		
		
		//construct UserEntity instance
		UserEntity user = new UserEntity();
		user.setUsername(signup.get("username"));
		//Hash password
		String hashed = BCrypt.hashpw(signup.get("password"), BCrypt.gensalt());
		user.setPassword_salthash(hashed);
		user.setFirstname(signup.get("firstname").toUpperCase());
		user.setLastname(signup.get("lastname").toUpperCase());
		user.setPhone(signup.get("mobile"));
		
	
		
		
		//preview finish, start sql inserting.
		boolean updateFlag = AuthorizeMapper.createNewUserByForm(user);
		if(!updateFlag) {
			rs.put("response_code", "500");
			//user table insert failed
			rs.put("response_text", "db insert failed");
		}
		
		//construct InvitationRecord instance
		if(invitationEntityId!=null) {
			InvitationRecordEntity invitationRecord = new InvitationRecordEntity();
			invitationRecord.setInvitation_date(new Date());
			invitationRecord.setUserid(AuthorizeMapper.getOneByUsername(user.getUsername()).getUserid());
			invitationRecord.setInvitation_entityId(Long.parseLong(invitationEntityId));
			
			updateFlag = AuthorizeMapper.createInvitationRecord(invitationRecord);
		}
		
		if(!updateFlag) {
			rs.put("response_code", "501");
			//invitation_record table insert failed
			rs.put("response_text", "db insert failed");
		}else {
			rs.put("response_code", "200");
			rs.put("response_text", "ok");
		}
		
		
		
		return rs;
	}
	
	public Map<String, String> getUserInfoByToken(Map<String, String> user) {
		Map<String,String> rs = new HashMap<String,String>();
	
		return rs;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
