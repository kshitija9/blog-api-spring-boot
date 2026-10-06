package com.telusko.blog.exception;

public class PostNotFoundException extends RuntimeException{
	public PostNotFoundException(String message) {
		super(message);
	}
	

}
