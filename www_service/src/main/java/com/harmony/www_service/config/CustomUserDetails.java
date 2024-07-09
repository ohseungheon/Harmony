package com.harmony.www_service.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.harmony.www_service.dto.UserDto;

public class CustomUserDetails implements UserDetails{
	
	private UserDto userDto;
	
	public CustomUserDetails(UserDto userDto) {
		this.userDto = userDto;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				
				return userDto.getRole();
				
			}
		});
		
		return collection;
	}

	@Override
	public String getPassword() {

		return userDto.getPassword();
	}

	@Override
	public String getUsername() {
		
		return userDto.getUsername();
	}
	

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
