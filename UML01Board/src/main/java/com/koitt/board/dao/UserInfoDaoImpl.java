package com.koitt.board.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.BoardException;
import com.koitt.board.model.CommonException;
import com.koitt.board.model.UserInfo;

@Repository
public class UserInfoDaoImpl implements UserInfoDao{

	private Logger logger = LogManager.getLogger(this.getClass());
	
	private static final String MAPPER_NAMESPACE = UserInfoDaoImpl.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public UserInfoDaoImpl() {
		super();
	}

	@Override
	public List<UserInfo> selectAll() throws CommonException{
		// TODO Auto-generated method stub
		List<UserInfo> list = null;
		
		try {
			list = session.selectList(MAPPER_NAMESPACE + ".selectAll"); 
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("Error / 게시물 전체 검색 실패.");
		}
		return list;
	}

	@Override
	public UserInfo select(String email) throws CommonException{
		// TODO Auto-generated method stub
		UserInfo item = new UserInfo();
		
		try {
			item = session.selectOne(MAPPER_NAMESPACE + ".select", email);
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.getMessage());
			throw new CommonException("Error / 사용자 검색 실패.");
		}
		
		
		return item;
	}

	@Override
	public void insert(UserInfo user) {
		// TODO Auto-generated method stub
		try {
			session.insert(MAPPER_NAMESPACE + ".insert", user); 
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			throw new CommonException("Error / 사용자 등록 실패");
		}
	}

	@Override
	public void insertUserTypes(UserInfo user) throws CommonException {
		// TODO Auto-generated method stub
		try {
			session.insert(MAPPER_NAMESPACE + ".insertUserTypes", user);
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("Error / 사용자 등록 실패");
		}
	}

	@Override
	public void delete(String email) throws CommonException{
		// TODO Auto-generated method stub
		try {
			session.delete(MAPPER_NAMESPACE+ ".delete", email);
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("Error / 사용자 삭제 실패");
		}
		
	}

	@Override
	public void deleteUserTypes(String email) throws CommonException{
		// TODO Auto-generated method stub
		try {
			session.delete(MAPPER_NAMESPACE+ ".deleteUserTypes", email);
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new CommonException("Error / 사용자 타입 삭제 실패");
		}
	}

	@Override
	public void update(UserInfo user) {
		// TODO Auto-generated method stub
		try {
			session.update(MAPPER_NAMESPACE + ".update", user);
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new BoardException("E07: 게시물 수정 실패");
		}
	}
	}


