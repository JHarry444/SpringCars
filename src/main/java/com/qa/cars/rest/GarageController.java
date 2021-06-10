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

import com.qa.cars.domain.Garage;
import com.qa.cars.dto.GarageDTO;
import com.qa.cars.service.GarageService;

@RestController
@RequestMapping("/garage")
public class GarageController {

	private GarageService service;

	@Autowired
	public GarageController(GarageService service) {
		super();
		this.service = service;
	}

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String hello() {
		return "Hello, World!";
	}

	@PostMapping("/create")
	public Garage createGarage(@RequestBody Garage garage) {
		return this.service.createGarage(garage);
	}

	@GetMapping("/find/{id}")
	public Garage find(@PathVariable int id) {
		return this.service.findGarage(id);
	}

	@GetMapping("/")
	public List<GarageDTO> getGarages() {
		return this.service.getGarages();
	}

	@PutMapping("/update/{id}")
	public Garage updateGarage(@RequestBody Garage garage, @PathVariable int id) {
		return this.service.updateGarage(id, garage);
	}

	@DeleteMapping("/remove/{id}")
	public boolean delete(@PathVariable int id) {
		return this.service.delete(id);
	}

}
