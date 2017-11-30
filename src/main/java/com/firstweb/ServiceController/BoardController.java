package com.firstweb.ServiceController;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.firstweb.Vo.BoardVo;
import com.firstweb.Vo.UserVo;
import com.firstweb.dao.BoardDao;
import com.firstweb.util.ImageUtil;

@Controller
public class BoardController {

	@Autowired
	BoardDao dao;

	@RequestMapping(value = "/memo_ins", method = RequestMethod.POST)
	public void memoInsert(HttpServletRequest req) {
		BoardVo vo = new BoardVo(req.getParameter("writter_id"), req.getParameter("content"));
		dao.board_ins(vo);
		System.out.println(vo);
	}

	@RequestMapping(value = "/free_ins", method = RequestMethod.POST)
	public void freeInsert(HttpServletRequest req){
		BoardVo vo = new BoardVo(req.getParameter("writter_id"), req.getParameter("content"), req.getParameter("title"),
				req.getParameter("permission"));
		MultipartHttpServletRequest multireq = (MultipartHttpServletRequest) req;	//이미지 받아오기
		MultipartFile imageFile = multireq.getFile(req.getParameter("image_name"));		//이미지 받아오기
		
		if (imageFile != null) {//이미지가 있을 경우만
			String dir_path;//사진이 저장될 경로. picture/유저명
			dir_path = "/picture/"+req.getParameter("writter_id");
			String contentType = imageFile.getOriginalFilename();//확장자명
			
			String fileName;//파일의 이름
			SimpleDateFormat time=new SimpleDateFormat("yyyyMMdd-hh:mm");
			fileName = req.getParameter("writter_id")+"-"+time.format(new Date(System.currentTimeMillis()))+contentType;
			
			this.CreateFile(imageFile, req, dir_path, fileName);
		}

		dao.board_ins(vo);
		dao.free_ins(vo);
		System.out.println(vo);
	}

	@RequestMapping(value = "/free_write")
	public String free_editor() {
		return "/free_write";
	}
	
	public String CreateFile(MultipartFile file, HttpServletRequest req, String dir_path, String fileName) {
		FileOutputStream fos = null;
		String root_path = ImageUtil.getRootPath(req);
		
		try {
			mkdir(root_path+dir_path);//경로가 없으면 경로를 만든다.
			String full_path = root_path+dir_path+"/"+fileName;//그를 이용해서 풀 경로를 만든다
			byte fileData[] = file.getBytes();
			
			fos = new FileOutputStream(full_path);
			fos.write(fileData);
			
			return "OK";
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(fos!=null)
					fos.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return "NO";
	}
	
	private String mkdir(String dir_path) {
		File dir = new File(dir_path);
		if(!dir.exists())
			dir.mkdirs();
		return "OK";
	}

}
