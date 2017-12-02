package com.koitt.board.service;

import javax.servlet.http.HttpServletRequest;

public interface FileService {
	
	// 파일 삭제
	public void remove(HttpServletRequest request, String filename);
}
