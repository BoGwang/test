package com.koitt.board.service;

import com.koitt.board.model.CommonException;
import com.koitt.board.model.UserInfo;

public interface UserInfoService {

	public UserInfo detail(String email) throws CommonException;

	public void newUser(UserInfo user) throws CommonException;

	public String delete(String email, String password) throws CommonException;

	public String modify(UserInfo user);

}
