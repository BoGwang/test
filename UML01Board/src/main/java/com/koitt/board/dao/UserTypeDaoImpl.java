package com.koitt.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.UserType;

@Repository
public class UserTypeDaoImpl implements UserTypeDao{
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private static final String MAPPER_NAMESPACE = UserTypeDaoImpl.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public UserTypeDaoImpl() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public UserType select(Integer id) throws CommonException{
		// TODO Auto-generated method stub
		UserType item = null;
		
		try {
			item = session.selectOne(MAPPER_NAMESPACE + ".select", id);
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e.getMessage());
			throw new CommonException("Error / UserType 조회 실패");
		} 
		
		return item;
	}

}
