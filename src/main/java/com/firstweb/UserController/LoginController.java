package com.firstweb.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/")
	public String MainPage() {
		return "/main";
	}
	
	@RequestMapping("/join")
	public String JoinPage() {
		return "/join";
	}
	
}
