package com.qa.cars.rest;

import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String hello() {
		return "Hello, World!";
	}
}
