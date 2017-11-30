package com.firstweb.util;

import javax.servlet.http.HttpServletRequest;

public class ImageUtil {
	
	public static String getRootPath(HttpServletRequest req) {
		String exactOs=System.getProperty("os.name");
		String os= exactOs.split(" ")[0];
		if(os.equals("Windows")) {
			return req.getSession().getServletContext().getRealPath("/");
		}else {
			return null;
		}
	}
}
