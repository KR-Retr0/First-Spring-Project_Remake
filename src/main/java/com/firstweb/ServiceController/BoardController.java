package com.firstweb.ServiceController;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.firstweb.Vo.BoardVo;
import com.firstweb.Vo.ImageVo;
import com.firstweb.Vo.UserVo;
import com.firstweb.dao.BoardDao;
import com.firstweb.util.PageNavigator;

@Controller
public class BoardController {

	@Autowired
	BoardDao dao;

	@RequestMapping(value = "/memo_ins", method = RequestMethod.POST)
	public String memoInsert(HttpServletRequest req) {
		BoardVo vo = new BoardVo(req.getParameter("writter_id"), req.getParameter("content"));
		dao.board_ins(vo);
		System.out.println(vo);
		
		return "/board_main";
	}

	@RequestMapping(value = "/free_ins", method = RequestMethod.POST)
	public ModelAndView freeInsert(HttpServletRequest req) {
		ModelAndView model = new ModelAndView("redirect:/board_main");
		BoardVo vo = new BoardVo(req.getParameter("writter_id"), req.getParameter("content"), req.getParameter("title"),
				req.getParameter("permission"));

		dao.board_ins(vo);
		dao.free_ins(vo);
		System.out.println(vo);
		
		model.addObject("board",vo);
		return model;
	}

	@RequestMapping(value = "/free_write")
	public String free_editor() {
		return "/free_write";
	}
	
	@RequestMapping(value = "/board_main")
	public ModelAndView MainBoard(HttpServletRequest req) {
		
		PageNavigator pn=new PageNavigator(0, dao.getTotalArticle(), "", 10, 5);
		ModelAndView mv = new ModelAndView("/board_main");
		
		System.out.println(pn.getNavigator());
		
		mv.addObject("pn",pn);
		
		return mv;
	}
	
	@RequestMapping(value = "/board_page")
	public ModelAndView MainBoard_page(HttpServletRequest req) {
		PageNavigator pn=new PageNavigator(req.getParameter("page"), dao.getTotalArticle(), "", 10, 5);
		
		ModelAndView mv = new ModelAndView("/board_main");
		System.out.println(pn.getNavigator());
		
		mv.addObject("pn",pn);
		
		return mv;
	}


}
