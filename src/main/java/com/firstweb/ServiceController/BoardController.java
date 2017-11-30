package com.firstweb.ServiceController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.firstweb.dao.BoardDao;

@Controller
public class BoardController {

	@Autowired
	BoardDao dao;
	
	@RequestMapping(value="/memo_ins", method=RequestMethod.POST)
	public void memoInsert(HttpServletRequest req) {
		
		dao.memo_ins(req.getParameter("writter_id"), req.getParameter("content"));
	}
	
}
