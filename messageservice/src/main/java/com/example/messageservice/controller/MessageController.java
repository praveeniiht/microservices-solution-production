package com.example.messageservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.messageservice.dto.MessageDto;
import com.example.messageservice.dto.MessageDtoList;
import com.example.messageservice.dto.MessageExceptionResponse;
import com.example.messageservice.exception.MessageException;
import com.example.messageservice.exception.MessageNotFoundException;
import com.example.messageservice.model.Message;
import com.example.messageservice.service.MessageService;

@RestController
@RequestMapping("/messageservice")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	
	@GetMapping("/test")
	public String getMessage() {
		return "Message Service working";
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<MessageDto>> findAll(){
		return new ResponseEntity(messageService.findAll(), HttpStatus.OK);
		
	}
	@PostMapping("/add")
	public ResponseEntity<MessageDto> addMessage(@RequestBody MessageDto message) {
		return new ResponseEntity(messageService.addMessage(message), HttpStatus.CREATED);
	}
	@GetMapping("/getMessageTo/{messageTo}")
	public ResponseEntity<List<MessageDto>> findAllByMessageTo(@PathVariable("messageTo") String messageTo){
		
		return new ResponseEntity(messageService.findAllByMessageTo(messageTo), HttpStatus.OK);
	}
	@GetMapping("/message/{msgId}")
	public ResponseEntity<MessageDto> findMessageById(@PathVariable("msgId")String msgId){
	
		return new ResponseEntity(messageService.findById(msgId), HttpStatus.OK);
		
	
	}
	
	@ExceptionHandler(MessageException.class)
	public ResponseEntity<MessageExceptionResponse> NotesHandler(MessageException ex){
		MessageExceptionResponse resp = 
				new MessageExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		
		ResponseEntity<MessageExceptionResponse> response = 
				new ResponseEntity<MessageExceptionResponse>(resp, HttpStatus.BAD_REQUEST);
		return response;
	}
	@ExceptionHandler(MessageNotFoundException.class)
	public ResponseEntity<MessageExceptionResponse> NotesHandler(MessageNotFoundException ex){
		MessageExceptionResponse resp = 
				new MessageExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		ResponseEntity<MessageExceptionResponse> response = 
				new ResponseEntity<MessageExceptionResponse>(resp, HttpStatus.NOT_FOUND);
		return response;
	}
	
}
