package com.firstweb.dao;

import org.apache.ibatis.annotations.Mapper;

import com.firstweb.Vo.UserVo;

@Mapper
public interface LoginDAO {
	public int IDCheck(String id);
	public int insertUser(UserVo vo);
	public UserVo login(String id, String pw);
}
