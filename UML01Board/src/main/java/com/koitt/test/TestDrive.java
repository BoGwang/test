package com.koitt.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.koitt.board.dao.UserInfoDaoImpl;

public class TestDrive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String password = "zetasxcv";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String crypt =  encoder.encode(password);
		System.out.println("Password : " + password );
		System.out.println("Encode : " + crypt);
		boolean isSame = encoder.matches(password, crypt);
		System.out.println("Password = Encode : " + isSame);
		String MAPPER_NAMESPACE = UserInfoDaoImpl.class.getName();
		System.out.println(MAPPER_NAMESPACE);
	}

}
