package com.example.messageservice.service;

import java.util.List;

import com.example.messageservice.dto.MessageDto;
import com.example.messageservice.model.Message;


public interface MessageService {
	public List<MessageDto> findAll();
	public MessageDto addMessage(MessageDto message);
	public List<MessageDto> findAllByMessageTo(String messageTo);
	public MessageDto findById(String msgId);
}
