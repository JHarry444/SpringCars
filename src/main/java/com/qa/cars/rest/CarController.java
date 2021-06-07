package com.qa.cars.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qa.cars.domain.Car;

@RestController
@RequestMapping("/cars")
public class CarController {

	private List<Car> cars = new ArrayList<>();

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String hello() {
		return "Hello, World!";
	}

	@PostMapping("/create")
	public String createCar(@RequestBody Car car) {
		this.cars.add(car);
		System.out.println(this.cars);
		return this.cars.toString();
	}

	@DeleteMapping("/remove/{id}")
	public String delete(@PathVariable int id) {
		this.cars.remove(id);
		return this.cars.toString();
	}

	@GetMapping("/")
	public List<Car> getCars() {
		return this.cars;
	}

}
