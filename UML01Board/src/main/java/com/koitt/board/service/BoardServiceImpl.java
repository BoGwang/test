package com.koitt.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koitt.board.dao.BoardDao;
import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao dao;
	
	public BoardServiceImpl() {}

	@Transactional // 에러가 났을 경우 자동으로 RollBack을 해주기 위함.
	// @Transactional 어노테이션을 붙이는 곳은 DB 안의 값에 직접적인 영향을 미치는 ((EX) insert, delete, update 등)곳에 붙임.
	@Override
	public void newBoard(Board board) throws BoardException {
		
		/*
		 * Java 코드로 Transaction 하는 방법
		 */
		try {
			
			// DB에서 다음 게시물의 번호를 가져온다.
			int no = dao.getBoardNo();
			board.setNo(no);
			
			// DB에 저장
			dao.insert(board);
			
		} catch (Exception e) {
			throw new BoardException("E05: 서비스 단계에서 글 등록 실패");
		}
	}

	@Override
	public Board detail(String no) throws BoardException {
		return dao.select(no);
	}

	@Override
	public List<Board> list() throws BoardException {
		return dao.selectAll();
	}

	@Override
	public int count() throws BoardException {
		return dao.boardCount();
	}

	@Transactional
	@Override
	public String modify(Board board) throws BoardException {
		/*
		 *  파라메터의 board 객체는 이미 수정된 정보를 담고 있다.
		 *  따라서 기존 데이터베이스에서 글번호로 기존 데이터를 불러온다.
		 */
		Board item = dao.select(Integer.toString(board.getNo()));
		String oldFilename = item.getAttachment();
		dao.update(board);
		
		return oldFilename;
	}

	@Transactional
	@Override
	public String remove(String no) throws BoardException {
		Board item = dao.select(no);
		String filename = item.getAttachment();
		dao.delete(no);
		
		return filename;
	}

}
