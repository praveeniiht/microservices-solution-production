package com.example.messageservice.exception;

public class MessageNotFoundException extends RuntimeException{
	public MessageNotFoundException(String error) {
		super(error);
	}

}
