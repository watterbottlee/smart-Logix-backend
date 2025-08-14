package com.mover.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mover.entities.User;
import com.mover.payloads.LoginRequestDto;
import com.mover.payloads.LoginResponseDto;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthUtil authUtil;

	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
		);
		
		User user = (User) authentication.getPrincipal();
		String token = authUtil.generateAccessToken(user);
		
		return new LoginResponseDto(token);
	}

}
