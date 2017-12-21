package com.firstweb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.firstweb.Vo.BoardVo;
import com.firstweb.Vo.ImageVo;

@Mapper
public interface BoardDao {
	public int board_ins(BoardVo vo);
	public int free_ins(BoardVo vo);
	public BoardVo getMemo();
	public List<BoardVo> getFreeBoardList(int start, int contentCount);
	public int getTotalArticle();
}
