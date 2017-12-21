package com.firstweb.UserController;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.firstweb.Vo.UserVo;
import com.firstweb.dao.LoginDAO;

@Controller
public class LoginController {
	@Autowired
	HttpSession session;

	@Autowired
	LoginDAO loginDao;

	@RequestMapping("/")
	public String MainPage() {
		return "/main";
	}

	@RequestMapping("/join")
	public String JoinPage() {
		return "/join";
	}

	@RequestMapping(value = "/join_req", method = RequestMethod.POST)
	public ModelAndView join_proc(HttpServletRequest req, UserVo userVo) {
		String likeArray[] = req.getParameterValues("like");
		String like = "";
		ModelAndView mv = new ModelAndView("/join_suc");
		
		String sns=req.getParameter("jsonStr");

		for (int i = 0; i < likeArray.length; i++) {
			like = like + likeArray[i] + ",";
		}
		like = like.substring(0, like.length() - 1);

		UserVo vo = new UserVo(req.getParameter("id"), req.getParameter("pw"), req.getParameter("name"),
				req.getParameter("gender"), like, req.getParameter("joinfrom"), sns);

		System.out.println(vo.toString());
		loginDao.insertUser(vo);

		return mv;
	}

	@RequestMapping("/login_req")
	public String Login(HttpServletRequest req) throws UnsupportedEncodingException {
		UserVo user = loginDao.login(req.getParameter("id"), req.getParameter("pw"));
		
		if(user!=null) {
			session.setAttribute("user", user);
			return "redirect:/board_main";
		}else {
			return "/main";
		}
		
	}

	@RequestMapping(value = "/join.ajax", produces = "application/text; charset=utf8")
	@ResponseBody
	public String checkId(HttpServletRequest req) throws UnsupportedEncodingException {

		System.out.println(req.getParameter("id") + "의 갯수" + loginDao.IDCheck(req.getParameter("id")));

		return loginDao.IDCheck(req.getParameter("id")) > 0 ? "중복" : "사용가능";
	}
}
