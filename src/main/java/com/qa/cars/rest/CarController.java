package com.qa.cars.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qa.cars.domain.Car;
import com.qa.cars.dto.CarDTO;
import com.qa.cars.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {

	private CarService service;

	@Autowired
	public CarController(CarService service) {
		super();
		this.service = service;
	}

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String hello() {
		return "Hello, World!";
	}

	@PostMapping("/create")
	public Car createCar(@RequestBody Car car) {
		return this.service.createCar(car);
	}

	@GetMapping("/find/{id}")
	public Car find(@PathVariable int id) {
		return this.service.findCar(id);
	}

	@GetMapping("/")
	public List<CarDTO> getCars() {
		return this.service.getCars();
	}

	@GetMapping("/findByMake/{make}")
	public List<Car> findByMake(@PathVariable String make) {
		return this.service.findByMake(make);
	}

	@PutMapping("/update/{id}")
	public Car updateCar(@RequestBody Car car, @PathVariable int id) {
		return this.service.updateCar(id, car);
	}

	@DeleteMapping("/remove/{id}")
	public boolean delete(@PathVariable int id) {
		return this.service.delete(id);
	}

}
