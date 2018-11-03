package com.hoshiumi.mathumi;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hoshiumi.mathumi.mapper.*;

@Controller
public class Rpcservlet {

	private static final String welcomemsg = "Welcome Mr. %s!";
	
	@Autowired
	private UserMapper UserMapper;
	
	
	@GetMapping("/welcome/user")
    @ResponseBody
    public String welcomeUser() {
		
        return UserMapper.getAll().toString();
    }

}
