package com.firstweb.Vo;

import java.sql.Date;

public class BoardVo {
	int id;
	String writter_id;
	String content;
	Date date;
	String title;
	String permission;
	String category=null;

	public BoardVo(String writter_id, String content, String title, String permission) {
		super();
		this.writter_id = writter_id;
		this.content = content;
		this.title = title;
		this.permission = permission;
		this.category = "free";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
		this.category = "memo";
	}

	public BoardVo() {
		super();
	}

	public String toString() {
		switch (category) {
		case "memo":
			return "[ New Content Inserted ] category : " + category + "\tcontent : " + content + "\t written by "
					+ writter_id;
		case "free":
			return "[ New Content Inserted ] category : " + category + "\ttitle : " + title + "\twritten by "
					+ writter_id;
		default:
			return null;
		}
	}

}
