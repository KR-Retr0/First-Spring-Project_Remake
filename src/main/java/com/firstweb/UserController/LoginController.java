package com.firstweb.UserController;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@Autowired
	HttpSession session;

	@RequestMapping("/")
	public String MainPage() {
		return "/main";
	}

	@RequestMapping("/join")
	public String JoinPage() {
		return "/join";
	}

	@RequestMapping("/login_req")
	public String Login(HttpServletRequest req) throws UnsupportedEncodingException{
		session.setAttribute("id", req.getParameter("id"));
		//session.getAttribute("id");
		return "/main";
	}
}
