package com.koitt.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.koitt.board.model.UserType;
import com.koitt.board.service.UserTypeService;

@Component
public class RoleToUserTypeConverter  implements Converter<Object, UserType>{

	@Autowired
	UserTypeService service;
	
	private Logger logger = LogManager.getLogger(this.getClass());
	// UserType을 id값으로 가져올때
	@Override
	public UserType convert(Object source) {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt((String) source);
		UserType userType = service.findById(id);
		logger.debug("User Type : " + userType);
		return userType;
	}

}
