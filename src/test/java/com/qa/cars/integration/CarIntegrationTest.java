package com.qa.cars.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cars.domain.Car;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // prevents port conflicts
@AutoConfigureMockMvc
public class CarIntegrationTest {

	@Autowired
	private MockMvc mvc; // allows us to send mock requests

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Car testCar = new Car("BMW", "520", "Black");
		String testCarAsJSON = this.mapper.writeValueAsString(testCar);

		Car testSavedCar = new Car("BMW", "520", "Black");
		testSavedCar.setId(1);
		String testSavedCarAsJSON = this.mapper.writeValueAsString(testSavedCar);

		RequestBuilder mockRequest = post("/cars/create").content(testCarAsJSON)
				.contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(testSavedCarAsJSON);

//		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);

		MvcResult result = this.mvc.perform(mockRequest).andExpect(checkStatus).andReturn();

		String responseBody = result.getResponse().getContentAsString();

		Car responseData = this.mapper.readValue(responseBody, Car.class);

		System.out.println("CAR: " + responseData);

		assertThat(responseData).isEqualTo(testSavedCar);

	}

}
