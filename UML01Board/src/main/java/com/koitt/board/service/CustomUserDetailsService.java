package com.koitt.board.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.koitt.board.model.UserInfo;
import com.koitt.board.model.UserType;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserInfoService service;
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub 
		UserInfo userInfo = service.detail(email);
		
		logger.debug("UserInfo : " + userInfo);
		
		if (userInfo == null) {
			throw new UsernameNotFoundException("E20 : 사용자 정보를 찾는데 실패했습니다.");
		}
		
		return new User(userInfo.getEmail(), userInfo.getPassword(), true, true, true, true, getGrantedAuthorities(userInfo));
		// enabled : 계정을 사용 가능한가? / accountNonExpired : 계정이 만료되었는가?, true면 만료되지 않음. 
		// accountNonLocked : 계정이 잠겨있는가?, true면 잠겨 있지 않음. 
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(UserInfo userInfo) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for (UserType item : userInfo.getUserTypes()) {
			logger.debug("UserType : " + item);
			authorities.add(new SimpleGrantedAuthority(item.getType()));
		}
		logger.debug("authorities : " + authorities);
		return authorities;
	}

}
