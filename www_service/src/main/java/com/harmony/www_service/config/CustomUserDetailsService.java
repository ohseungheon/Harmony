package com.harmony.www_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.LoginDao;
import com.harmony.www_service.dto.UserDto;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDto userDto = loginDao.findByUsername(username);
		
		if(userDto != null) {
			
			return new CustomUserDetails(userDto);
		}
		
		throw new UsernameNotFoundException("로그인 실패");
	}
	
	

}
