package com.example.messageservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageExceptionResponse {
	private String message;
	private long timeStamp;
	private int status;
}
