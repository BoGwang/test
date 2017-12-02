package com.koitt.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private static final String MAPPER_NAMESPACE = BoardDaoImpl.class.getName();
	
	@Autowired
	private SqlSession session;

	public BoardDaoImpl() {}

	@Override
	public int getBoardNo() throws BoardException {
		// Nothing to do
		return 0;
	}

	@Override
	public void insert(Board board) throws BoardException {
		try {
			session.insert(MAPPER_NAMESPACE + ".insert", board);
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new BoardException("E04: 게시물 등록 실패");
		}
	}

	@Override
	public Board select(String no) throws BoardException {
		Board board = null;
		
		try {
			board = session.selectOne(MAPPER_NAMESPACE + ".select", no);
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new BoardException("E01: 게시물 검색 실패");
		}
		
		return board;
	}

	@Override
	public List<Board> selectAll() throws BoardException {
		List<Board> list = null;
		
		try {
			list = session.selectList(MAPPER_NAMESPACE + ".selectAll");
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new BoardException("E02: 게시물 전체 검색 실패");
		}
		
		return list;
	}

	@Override
	public int boardCount() throws BoardException {
		Integer count = 0;
		
		try {
			count = session.selectOne(MAPPER_NAMESPACE + ".count");
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new BoardException("E09: 글 개수 가져오기 실패");
		}
		
		return count;
	}

	@Override
	public void update(Board board) throws BoardException {
		
		try {
			session.update(MAPPER_NAMESPACE + ".update", board);
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new BoardException("E07: 게시물 수정 실패");
		}
	}

	@Override
	public void delete(String no) throws BoardException {		
		try {
			session.delete(MAPPER_NAMESPACE + ".delete", no);
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new BoardException("E08: 게시물 삭제 실패");
		}
	}

}
