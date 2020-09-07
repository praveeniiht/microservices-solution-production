package com.example.messageservice.functional;

import static com.example.microservice.utils.TestUtils.businessTestFile;
import static com.example.microservice.utils.TestUtils.currentTest;
import static com.example.microservice.utils.TestUtils.yakshaAssert;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.messageservice.controller.MessageController;
import com.example.messageservice.dto.MessageDto;
import com.example.messageservice.service.MessageService;
import com.example.microservice.utils.MasterData;


@WebMvcTest(MessageController.class)
@RunWith(SpringRunner.class)
class MessageControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MessageService messageService;
	
	@Test
	public void testFindAllMessages() throws Exception {
		List<MessageDto> list = new ArrayList<MessageDto>();
		list.add(MasterData.getMessageDto());
		Mockito.when(messageService.findAll()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messageservice/all")
				.content(MasterData.asJsonString(MasterData.getMessageDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		System.out.println(MasterData.asJsonString(list));
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(list)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	 void testFindAllMessagesBDD() throws Exception {
		final int count[] = new int[1];
		MessageDto messagedto = MasterData.getMessageDto();
		List<MessageDto> list = new ArrayList<>();
		Mockito.when(messageService.findAll()).then(new Answer<List<MessageDto>>() {

			@Override
			public List<MessageDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Called");
				count[0]++;
				return list;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messageservice/all")
				.content(MasterData.asJsonString(messagedto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(count[0]);
	
		yakshaAssert(currentTest(),count[0] == 1? true : false,businessTestFile);
		
	}
	@Test
	 void testAddMessage() throws Exception{
		MessageDto messagedto = MasterData.getMessageDto();
		Mockito.when(messageService.addMessage(messagedto))
		.thenReturn(messagedto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/messageservice/add")
				.content(MasterData.asJsonString(messagedto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(messagedto);
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(messagedto))? true : false,
				businessTestFile);
	}
	@Test
	void testAddMessageBDD() throws Exception{
		final int count[] = new int[1];
		MessageDto messagedto = MasterData.getMessageDto();
		Mockito.when(messageService.addMessage(messagedto)).then(new Answer<MessageDto>() {

			@Override
			public MessageDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Called");
				count[0]++;
				return messagedto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/messageservice/add")
				.content(MasterData.asJsonString(messagedto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(count[0]);
	
		yakshaAssert(currentTest(),count[0] == 1? true : false,businessTestFile);
	}
	
	@Test
	void testFindAllByMessageTo() throws Exception{
		
		List<MessageDto> list = new ArrayList<MessageDto>();
		list.add(MasterData.getMessageDto());
		Mockito.when(messageService.findAllByMessageTo("Kishore")).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messageservice/getMessageTo/Kishore")
				.content(MasterData.asJsonString(MasterData.getMessageDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(list)) ? "true"
						: "false"),
				businessTestFile);

		
	}
	@Test
	 void testFindAllByMessageToBDD() throws Exception {
		final int count[] = new int[1];
		MessageDto messagedto = MasterData.getMessageDto();
		List<MessageDto> list = new ArrayList<>();
		list.add(messagedto);
		Mockito.when(messageService.findAllByMessageTo("Kishore")).then(new Answer<List<MessageDto>>() {

			@Override
			public List<MessageDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Called");
				count[0]++;
				return list;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messageservice/getMessageTo/Kishore")
				.content(MasterData.asJsonString(messagedto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(count[0]);
		yakshaAssert(currentTest(),count[0] == 1? true : false,businessTestFile);
		
	}
	@Test
	void testFindMessageById() throws Exception{
	MessageDto messagedto = MasterData.getMessageDto();
		Mockito.when(messageService.findById("m1001")).thenReturn(messagedto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messageservice/message/m1001")
				.content(MasterData.asJsonString(MasterData.getMessageDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(messagedto)) ? "true"
						: "false"),
				businessTestFile);
		
	}
	@Test
	 void testFindMessageByIdBDD() throws Exception {
		final int count[] = new int[1];
		MessageDto messagedto = MasterData.getMessageDto();
		Mockito.when(messageService.findById("m1001")).then(new Answer<MessageDto>() {

			@Override
			public MessageDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Called");
				count[0]++;
				return messagedto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messageservice/message/m1001")
				.content(MasterData.asJsonString(messagedto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(count[0]);
		yakshaAssert(currentTest(),count[0] == 1? true : false,businessTestFile);
		
	}
	

}
