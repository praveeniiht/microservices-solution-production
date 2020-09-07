package com.example.messageservice.boundary;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.messageservice.dto.MessageDto;
import static com.example.microservice.utils.MasterData.*;
import static com.example.microservice.utils.TestUtils.*;

public class MessageBoundaryTest {
	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testMessageFromNameLength() throws Exception {
		MessageDto messagedto = getMessageDto();
		messagedto.setMessageFrom("praveen");
		Set<ConstraintViolation<MessageDto>> violations = validator.validate(messagedto);
		yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testMessageToNameLength() throws Exception {
		MessageDto messagedto = getMessageDto();
		messagedto.setMessageTo("Kishore");
		Set<ConstraintViolation<MessageDto>> violations = validator.validate(messagedto);
		yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
	}
	@Test
	public void testMessageLength() throws Exception {
		MessageDto messagedto = getMessageDto();
		messagedto.setMessage("Hi Kishore please add some sql notes too");
		Set<ConstraintViolation<MessageDto>> violations = validator.validate(messagedto);
		yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
	}

	

}
