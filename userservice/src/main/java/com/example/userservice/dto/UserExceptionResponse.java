package com.example.userservice.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExceptionResponse {
	private String message;
	private long timeStamp;
	private int status;
}
