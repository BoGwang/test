package com.koitt.board.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
	
	private static final String UPLOAD_FOLDER = "/upload";

	@Override
	public void remove(HttpServletRequest request, String filename) {
		String path = request.getServletContext().getRealPath(UPLOAD_FOLDER);
		
		// 서버에 저장된 파일을 불러와서 객체화 시킴
		File file = new File(path, filename);
		
		// 파일이 존재하면 파일을 삭제한다.
		if (file.exists()) {
			file.delete();
		}
	}

}
