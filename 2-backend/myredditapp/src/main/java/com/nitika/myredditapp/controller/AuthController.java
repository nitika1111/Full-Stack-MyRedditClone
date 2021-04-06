package com.nitika.myredditapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitika.myredditapp.dto.AuthenticationResponse;
import com.nitika.myredditapp.dto.LoginRequest;
import com.nitika.myredditapp.dto.RegisterRequest;
import com.nitika.myredditapp.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController  {

	private AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService= authService;
		System.out.println("-------> Niti: Inside AuthController constructor");
	}
	
	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
		System.out.println("-------> Niti: Inside AuthController->signup(): Calling authService.signup");
		System.out.println("-------> Niti: calling signup() with parameter registerRequest: "+registerRequest.toString());
		

		authService.signup(registerRequest);
		System.out.println("-------> Niti: Inside AuthController->signup(): Returned from authService.signup");

		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@GetMapping("/accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token){
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account activated successfully", HttpStatus.OK); 
	}
	
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		System.out.println("-------> Niti: Inside AuthController->login()");

		return authService.login(loginRequest);
		
	}
}





















