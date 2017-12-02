package com.koitt.board.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koitt.board.dao.UserInfoDao;
import com.koitt.board.model.Board;
import com.koitt.board.model.CommonException;
import com.koitt.board.model.UserInfo;
import com.koitt.board.model.UserType;
import com.koitt.board.model.UserTypeId;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoDao dao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserInfo detail(String email) throws CommonException{
		// TODO Auto-generated method stub
		return dao.select(email);
	}

	@Transactional
	@Override
	public void newUser(UserInfo user) throws CommonException{
		// TODO Auto-generated method stub
		user.setPassword(encoder.encode(user.getPassword()));
		
		// 사용자 권한 USER 추가
		UserType userAuth = new UserType();
		userAuth.setId(UserTypeId.USER.getUserTypeId());
		userAuth.setType(UserTypeId.USER.name());
		Set<UserType> userTypes = new HashSet<>();
		
		userTypes.add(userAuth);
		
		user.setUserTypes(userTypes);
		
		dao.insert(user);
		dao.insertUserTypes(user);
	}
	@Transactional
	@Override
	public String delete(String email, String password) throws CommonException{
		// TODO Auto-generated method stub
		UserInfo item = dao.select(email);
		boolean isMatched = encoder.matches(password, item.getPassword());
		if (isMatched) {
			dao.deleteUserTypes(email);
			dao.delete(email);
		}

		return item.getAvatar();
	}

	@Override
	public String modify(UserInfo user) {
		// TODO Auto-generated method stub
		UserInfo item = dao.select(user.getEmail());
		String oldFilename = item.getAvatar();
		dao.update(user);
		
		return oldFilename;
	}
	

}
