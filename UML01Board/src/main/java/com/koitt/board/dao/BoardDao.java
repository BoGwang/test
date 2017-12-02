package com.koitt.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;

public interface BoardDao {

	// 게시판 다음 글번호를 리턴
	public int getBoardNo() throws BoardException;
	
	// 게시판 글 등록
	public void insert(Board board) throws BoardException;
	
	// 게시판 글 하나 가져오기
	public Board select(String no) throws BoardException;
	
	// 전체 글 가져오기
	public List<Board> selectAll() throws BoardException;
	
	// 전체 글 개수 가져오기
	public int boardCount() throws BoardException;
	
	// 글 수정하기
	public void update(Board board) throws BoardException;
	
	// 글 삭제하기
	public void delete(String no) throws BoardException;
}
