package com.koitt.board.controller;

import java.io.File;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;
import com.koitt.board.model.CommonException;
import com.koitt.board.model.UserInfo;
import com.koitt.board.service.FileService;
import com.koitt.board.service.UserInfoService;
import com.koitt.board.service.UserTypeService;

@Controller
public class UserWebController {

	private static final String UPLOAD_FOLDER = "/avatar";

	@Autowired
	private UserInfoService userInfoService; // UserInfoService에 @Service 어노테이션을 붙여놓았기 때문에 @Autowired 사용가능.

	@Autowired
	private UserTypeService userTypeService;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "user/login";
	}


	@RequestMapping(value = "/logout" , method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth!=null) { // 로그인이 되어있을때 auth는 null값이 아니다.
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login?action=logout";

	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model) {
		model.addAttribute("userInfo", this.getPrincipal());

		return "user/admin";
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public String accessDeniedPage(Model model) {
		model.addAttribute("email", this.getPrincipal());
		return "user/access-denied";
	}

	// 현재 접속한 사용자의 email
	private String getPrincipal() {
		// TODO Auto-generated method stub
		String username = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}
		else { // 일종의 예외처리
			username = principal.toString();
		}

		return username;
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinPage() {

		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String newUser(HttpServletRequest request,
			String email,
			String password,
			String name,
			@RequestParam("avatar") MultipartFile avatar)
					throws BoardException, Exception {

		UserInfo user = new UserInfo();
		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);

		// 최상위 경로 밑에 upload 폴더의 경로를 가져온다.
		String path = request.getServletContext().getRealPath(UPLOAD_FOLDER);

		// MultipartFile 객체에서 파일명을 가져온다.
		String originalName = avatar.getOriginalFilename();

		// upload 폴더가 없다면, upload 폴더 생성
		File directory = new File(path);
		if (!directory.exists()) {
			directory.mkdir();
		}

		// attachment 객체를 이용하여, 파일을 서버에 전송
		if (avatar != null && !avatar.isEmpty()) {
			int idx = originalName.lastIndexOf(".");
			String fileName = originalName.substring(0, idx);
			String ext = originalName.substring(idx, originalName.length());
			String uploadFilename = fileName
					+ Long.toHexString(System.currentTimeMillis())
					+ ext;
			avatar.transferTo(new File(path, uploadFilename));
			uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
			user.setAvatar(uploadFilename);
		}

		userInfoService.newUser(user);

		return "redirect:login";
	}

	@RequestMapping(value = "/user/setting" , method = RequestMethod.GET)
	public String setting() {
		return "user/setting";
	}

	@RequestMapping(value = "/user/delete" , method = RequestMethod.GET)
	public String delete() {
		return "user/delete";
	}

	@RequestMapping(value = "/user/delete" , method = RequestMethod.POST)
	public String delete(HttpServletRequest request, String password ) {

		String filename = userInfoService.delete(this.getPrincipal(), password);
		if (filename != null && !filename.trim().isEmpty()) {
			fileService.remove(request, filename);
		}

		return "redirect:/logout";
	}

	// 글 수정하기 화면
	@RequestMapping(value = "/user/modify", method = RequestMethod.GET)
	public String modify(Model model) throws CommonException {

		UserInfo item = null;

		item = userInfoService.detail(this.getPrincipal());

		model.addAttribute("item", item);

		return "user/modify";
	}

	// 글 수정 후, 글 목록 화면으로 이동
	@RequestMapping(value = "/user/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest request,
			String email,
			String oldPassword,
			String newPassword,
			String name,
			@RequestParam("avatar") MultipartFile avatar)
					throws CommonException,Exception {
		
		// 기존 비밀번호와 비교하여 같으면 수정허용, 아니면 다른 페이지 이동
		UserInfo item = userInfoService.detail(email);
		if (!encoder.matches(oldPassword, item.getPassword())) {
			return "password-not-matched";
		}

		UserInfo user = new UserInfo();
		user.setEmail(email);
		user.setPassword(encoder.encode(newPassword));
		user.setName(name);

		String path = request.getServletContext().getRealPath(UPLOAD_FOLDER);
		String originalName = avatar.getOriginalFilename();

		// attachment 객체를 이용하여, 파일을 서버에 전송
		if (avatar != null && !avatar.isEmpty()) {
			int idx = originalName.lastIndexOf(".");
			String fname = originalName.substring(0, idx);
			String ext = originalName.substring(idx, originalName.length());
			String uploadFilename = fname
					+ Long.toHexString(System.currentTimeMillis())
					+ ext;
			avatar.transferTo(new File(path, uploadFilename));
			uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
			user.setAvatar(uploadFilename);
		}

		String oldFilename = userInfoService.modify(user);
		if (oldFilename != null && !oldFilename.trim().isEmpty()) {
			fileService.remove(request, oldFilename);
		}

		return "redirect:/user/setting";
	}
}
