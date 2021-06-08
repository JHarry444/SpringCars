package com.qa.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cars.domain.Car;
import com.qa.cars.rest.CarController;

@SpringBootApplication
public class SpringCarsApplication {

	public static void main(String[] args) throws JsonProcessingException {
		// FOR DEMO PURPOSES ONLY
		ApplicationContext beanBag = SpringApplication.run(SpringCarsApplication.class, args);

		CarController controller = beanBag.getBean(CarController.class);

		System.out.println(controller);

		ObjectMapper mapper = beanBag.getBean(ObjectMapper.class);

		System.out.println(mapper.writeValueAsString(new Car("Fiat", "Panda", "White")));

		String message = beanBag.getBean(String.class);

		System.out.println(message);
	}

}
