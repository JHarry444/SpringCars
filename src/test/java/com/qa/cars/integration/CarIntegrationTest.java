package com.qa.cars.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cars.domain.Car;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // prevents port conflicts
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:car-schema.sql",
		"classpath:car-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
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
		testSavedCar.setId(2);
		String testSavedCarAsJSON = this.mapper.writeValueAsString(testSavedCar);

		RequestBuilder mockRequest = post("/cars/create").content(testCarAsJSON)
				.contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(testSavedCarAsJSON);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);

//		MvcResult result = this.mvc.perform(mockRequest).andExpect(checkStatus).andReturn();
//
//		String responseBody = result.getResponse().getContentAsString();
//
//		Car responseData = this.mapper.readValue(responseBody, Car.class);
//
//		System.out.println("CAR: " + responseData);
//
//		assertThat(responseData).isEqualTo(testSavedCar);

	}

	@Test
	void testGetAll() throws Exception {
		Car testCar = new Car(1, "Fiat", "Panda", "white");
		List<Car> testCars = List.of(testCar);
		String testCarsAsJSONArray = this.mapper.writeValueAsString(testCars);

		this.mvc.perform(get("/cars/")).andExpect(status().isOk()).andExpect(content().json(testCarsAsJSONArray));

	}

}
