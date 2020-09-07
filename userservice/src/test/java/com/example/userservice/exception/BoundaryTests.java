package com.example.userservice.exception;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.userservice.dto.UserDto;
import static com.example.userservice.utils.TestUtils.boundaryTestFile;
import static com.example.userservice.utils.TestUtils.currentTest;
import static com.example.userservice.utils.TestUtils.yakshaAssert;

public class BoundaryTests {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testAuthorNameLength() throws Exception {
		UserDto data = new UserDto();
		data.setAuthor("praveen");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(data);
		yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPasswordLength() throws Exception {
		UserDto data = new UserDto();
		data.setPassword("DevOps");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(data);
		yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);

	}

	@Test
	public void testEmailFormat() throws Exception {
		UserDto data = new UserDto();
		data.setEmail("praveen@gmail.com");
		
		Set<ConstraintViolation<UserDto>> violations = validator.validate(data);
		yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);

	}

}
