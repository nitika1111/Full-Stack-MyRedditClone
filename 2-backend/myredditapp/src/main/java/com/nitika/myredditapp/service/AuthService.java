package com.nitika.myredditapp.service;

import com.nitika.myredditapp.dto.RegisterRequest;

public interface AuthService {

	void signup(RegisterRequest registerRequest);
	
	void verifyAccount(String token);
}
