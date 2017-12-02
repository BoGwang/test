package com.koitt.board.model;

public class UserType {
	private Integer id; // 타입의 인덱스 번호
	private String type; // 타입 명
	
	public UserType() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public UserType(Integer id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	@Override
	public int hashCode() {
		int result = id.hashCode() + type.hashCode();
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
		if (!(obj instanceof UserType)) {
			return false;
		}
		
		UserType item = (UserType) obj;
		
		if (this.id.equals(item.id)) {
			return true;
		}
		
		
		return false;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserType [id=");
		builder.append(id);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
