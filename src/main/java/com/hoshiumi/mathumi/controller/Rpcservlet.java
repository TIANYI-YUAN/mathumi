package com.hoshiumi.mathumi.controller;



import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoshiumi.mathumi.Welcome;
import com.hoshiumi.mathumi.mapper.*;

@Controller
public class Rpcservlet {

	private static final String welcomemsg = "Welcome Mr. %s!";
	
	@Autowired
	private AuthorizeController AuthorizeController;
	
	
	@RequestMapping(value="/rpc/test",
			method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8080")
    public Welcome welcomeUser(@RequestParam(name="name", required=false, defaultValue="Java Fan") String name) {
        return new Welcome(String.format(welcomemsg, name));
    }
	
	
}
