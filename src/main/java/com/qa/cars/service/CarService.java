package com.qa.cars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.cars.domain.Car;
import com.qa.cars.dto.CarDTO;
import com.qa.cars.repo.CarRepo;

@Service
public class CarService {

	private CarRepo repo;

	@Autowired
	public CarService(CarRepo repo) {
		super();
		this.repo = repo;
	}

	public Car createCar(Car car) {
		return this.repo.save(car);
	}

	public Car findCar(Integer id) {
		Optional<Car> optionalCar = this.repo.findById(id);
		return optionalCar.orElseThrow(() -> new EntityNotFoundException());
	}

	public List<Car> findByMake(String make) {
		return this.repo.findByMakeIgnoreCase(make);
	}

	public Car updateCar(Integer id, Car newData) {
		Car existing = this.findCar(id); // fetch existing from db

		existing.setMake(newData.getMake()); // update the values
		existing.setModel(newData.getModel());
		existing.setColour(newData.getColour());

		Car updated = this.repo.save(existing); // save it back to overwrite original

		return updated;
	}

	public boolean delete(int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	private CarDTO mapToDTO(Car car) {
		CarDTO dto = new CarDTO();

		dto.setColour(car.getColour());
		dto.setId(car.getId());
		dto.setMake(car.getMake());
		dto.setModel(car.getModel());

		return dto;
	}

	// streams example
	private List<CarDTO> getCars_WITH_STREAMS() {
		return this.repo.findAll().stream().map(car -> this.mapToDTO(car)).collect(Collectors.toList());
	}

	public List<CarDTO> getCars() {
		List<Car> cars = this.repo.findAll();
		List<CarDTO> dtos = new ArrayList<>();

		CarDTO dto = null;
		for (Car car : cars) {
			dto = this.mapToDTO(car);
			dtos.add(dto);
		}

		return dtos;
	}

}
