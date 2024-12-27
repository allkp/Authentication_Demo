package com.authenticationapp.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.authenticationapp.config.JwtTokenProvider;
import com.authenticationapp.dto.LoginRequest;
import com.authenticationapp.dto.LoginResponse;
import com.authenticationapp.dto.UserDto;
import com.authenticationapp.model.User;
import com.authenticationapp.repo.UserRepository;


@Service
public class AuthService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Transactional
	public UserDto registerNewUser(UserDto dto) {
		if(repo.existsByUsername(dto.getUsername())) {
			throw new RuntimeException("User has Already registered...!!!");
		}
		
		if(repo.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Email is already in use....!!!");
		}
		
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setEmail(dto.getEmail());
		user.setRoles(Set.of(User.Role.ROLE_USER));
		user.setEnabled(true);
		
		User savedUser = repo.save(user);
		
		return convertToDto(savedUser);
	}

	public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), 
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return new LoginResponse(jwt);
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
