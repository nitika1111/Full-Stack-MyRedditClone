package com.nitika.myredditapp.exception;

public class PostNotFoundException extends RuntimeException{

	public PostNotFoundException(String message) {
		super(message);
	}
}
