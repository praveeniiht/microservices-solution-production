package com.example.userservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static com.example.userservice.utils.MasterData.*;
import static com.example.userservice.utils.TestUtils.*;
import java.util.ArrayList;
import java.util.List;
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
import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.MasterData;

@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService userService;

	@Test
	void testGetAllUsers() throws Exception {
		List<UserDto> list = new ArrayList<UserDto>();
		list.add(MasterData.getUserDto());
		Mockito.when(userService.findAll()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/userservice/all")
				.content(MasterData.asJsonString(MasterData.getUserDto())).contentType(MediaType.APPLICATION_JSON)
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
	void testGetAllUsersBDD() throws Exception {
		final int count[] = new int[1];
		UserDto userdto = MasterData.getUserDto();
		List<UserDto> list = new ArrayList<>();
		Mockito.when(userService.findAll()).then(new Answer<List<UserDto>>() {

			@Override
			public List<UserDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Called");
				count[0]++;
				return list;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/userservice/all")
				.content(MasterData.asJsonString(list))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(count[0]);
	
		yakshaAssert(currentTest(),count[0] == 1? true : false,businessTestFile);
		
	}

	@Test
	void testLogin() throws Exception {
		UserDto userdto = MasterData.getUserDto();
		
		Mockito.when(userService.login(userdto)).thenReturn(userdto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/userservice/login")
				.content(MasterData.asJsonString(MasterData.getUserDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userdto)) ? "true"
						: "false"),
				businessTestFile);
		
	}

	@Test
	void testLoginBDD() throws Exception {
		final int count[] = new int[1];
		UserDto userdto = MasterData.getUserDto();
		Mockito.when(userService.login(userdto)).then(new Answer<UserDto>() {

			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Called");
				count[0]++;
				return userdto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/userservice/login")
				.content(MasterData.asJsonString(userdto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(count[0]);
	
		yakshaAssert(currentTest(),count[0] == 1? true : false,businessTestFile);
	}
	
	@Test
	void testAddUser() throws Exception{
		UserDto userdto = MasterData.getUserDto();
		Mockito.when(userService.register(userdto))
		.thenReturn(userdto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/userservice/add")
				.content(MasterData.asJsonString(userdto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userdto))? true : false,
				businessTestFile);
	}
	
	@Test
	void testAddUserBDD() throws Exception{
		final int count[] = new int[1];
		UserDto userdto = MasterData.getUserDto();
		Mockito.when(userService.register(userdto)).then(new Answer<UserDto>() {

			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Called");
				count[0]++;
				return userdto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/userservice/add")
				.content(MasterData.asJsonString(userdto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(count[0]);
	
		yakshaAssert(currentTest(),count[0] == 1? true : false,businessTestFile);	
	}
	
}
