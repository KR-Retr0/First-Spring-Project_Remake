package com.firstweb.Vo;

import java.sql.Date;

public class BoardVo {
	String writter_id;
	String content;
	Date date;
	public String getWritter_id() {
		return writter_id;
	}
	public void setWritter_id(String writter_id) {
		this.writter_id = writter_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BoardVo(String writter_id, String content) {
		super();
		this.writter_id = writter_id;
		this.content = content;
	}
	public BoardVo() {
		super();
	}
	
	
}
