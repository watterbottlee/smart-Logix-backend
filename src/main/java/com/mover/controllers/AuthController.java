package com.mover.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mover.payloads.LoginRequestDto;
import com.mover.payloads.LoginResponseDto;
import com.mover.security.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("login")
	public ResponseEntity<LoginResponseDto> userLogin(@RequestBody LoginRequestDto loginRequestDto){
		return new ResponseEntity<>(authService.login(loginRequestDto),HttpStatus.OK);
	}
}	

