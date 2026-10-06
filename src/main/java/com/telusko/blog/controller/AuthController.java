package com.telusko.blog.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.blog.model.User;
import com.telusko.blog.repository.UserRepository;
import com.telusko.blog.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private JwtUtil jwtUtil;
	
	@Autowired 
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
		    return ResponseEntity.badRequest().body("Username already exists!");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if (user.getRole() == null || user.getRole().isEmpty()) {
		    user.setRole("USER");
		}
		userRepository.save(user);
		return ResponseEntity.ok("User registered successfully!");
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody User loginRequest) {
		authenticationManager.authenticate(
			    new UsernamePasswordAuthenticationToken(
			        loginRequest.getUsername(),
			        loginRequest.getPassword()
			    )
			);
		User user = userRepository.findByUsername(loginRequest.getUsername())
		        .orElseThrow(() -> new RuntimeException("User not found"));
		
		String token = jwtUtil.generateToken(user.getUsername());
		
		Map<String, String> response = new HashMap<>();
		response.put("token", token);
		response.put("username", user.getUsername());
		response.put("role", user.getRole());
		
		return ResponseEntity.ok(response);
	}

}
