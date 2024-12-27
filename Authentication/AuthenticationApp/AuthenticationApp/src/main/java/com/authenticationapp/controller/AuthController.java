package com.authenticationapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authenticationapp.dto.LoginRequest;
import com.authenticationapp.dto.LoginResponse;
import com.authenticationapp.dto.UserDto;
import com.authenticationapp.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto dto){
		return ResponseEntity.ok(authService.registerNewUser(dto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		return ResponseEntity.ok(authService.authenticateUser(loginRequest));
	}
}
