package com.example.microservice.utils;

import java.io.IOException;

import com.example.messageservice.dto.MessageDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MasterData {
	public static MessageDto getMessageDto() {
		MessageDto msgDto = new MessageDto();
		msgDto.setMessage("This is a test Message");
		msgDto.setMessageFrom("praveen");
		msgDto.setMessageTo("Kishore");
		msgDto.setNoteId(1001);
		return msgDto;
	}
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	public static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
	

}
