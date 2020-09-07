package com.example.userservice.utils;

import java.io.IOException;
import com.example.userservice.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MasterData {
	public static UserDto getUserDto() {
		UserDto userdto = new UserDto();
		userdto.setAuthor("praveen");
		userdto.setPassword("1234");
		userdto.setEmail("praveen@gmail.com");
		userdto.setMobile("9948121217");
		return userdto;
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
