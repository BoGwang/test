package com.koitt.board.model;

import java.util.Set;

public class UserInfo {
	private String email; // 이메일 (Primary Key)
	private String password; // 비밀번호
	private String name; // 이름
	private String avatar; // 아바타 이미지 파일명
	private Set<UserType> userTypes; // ADMIN, USER 
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}

	public UserInfo(String email, String password, String name, String avatar, Set<UserType> userTypes) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.avatar = avatar;
		this.userTypes = userTypes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Set<UserType> getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(Set<UserType> userTypes) {
		this.userTypes = userTypes;
	}

	@Override
	public int hashCode() {
		int result = email.hashCode() + avatar.hashCode() + name.hashCode() + password.hashCode() + userTypes.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		
		if (obj==null) {
			return false;
		}
		
		if (!(obj instanceof UserInfo)) {
			return false;
		}
		
		UserInfo item = (UserInfo) obj;
		
		if (this.email.equals(item.email)) {
			return true;
		}
		
		
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfo [email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", name=");
		builder.append(name);
		builder.append(", avatar=");
		builder.append(avatar);
		builder.append(", userTypes=");
		builder.append(userTypes);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
}
