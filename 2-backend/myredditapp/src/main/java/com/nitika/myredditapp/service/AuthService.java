package com.nitika.myredditapp.service;

import com.nitika.myredditapp.dto.AuthenticationResponse;
import com.nitika.myredditapp.dto.LoginRequest;
import com.nitika.myredditapp.dto.RegisterRequest;
import com.nitika.myredditapp.entity.User;

public interface AuthService {

	void signup(RegisterRequest registerRequest);
	
	void verifyAccount(String token);

	AuthenticationResponse login(LoginRequest loginRequest);

	User getCurrentUser();

	boolean isLoggedIn();
}
