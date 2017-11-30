package com.firstweb.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {
	public void memo_ins(String writter_id, String content);
}
