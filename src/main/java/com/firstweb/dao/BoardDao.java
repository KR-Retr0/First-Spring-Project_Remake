package com.firstweb.dao;

import org.apache.ibatis.annotations.Mapper;

import com.firstweb.Vo.BoardVo;

@Mapper
public interface BoardDao {
	public int board_ins(BoardVo vo);
	public int free_ins(BoardVo vo);
}
