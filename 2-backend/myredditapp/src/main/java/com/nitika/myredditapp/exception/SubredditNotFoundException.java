package com.nitika.myredditapp.exception;

public class SubredditNotFoundException extends RuntimeException{

	public SubredditNotFoundException(String message) {
		super(message);
	}
}
