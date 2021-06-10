package com.qa.cars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.qa.cars.domain.Car;
import com.qa.cars.dto.CarDTO;
import com.qa.cars.repo.CarRepo;
import com.qa.cars.utils.CarMapper;

@Service
public class CarService {

	private CarRepo repo;

	private CarMapper mapper;

	public CarService(CarRepo repo, CarMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public CarDTO createCar(Car car) {
		Car saved = this.repo.save(car);
		return this.mapper.mapToDTO(saved);
	}

	public CarDTO findCar(Integer id) {
		Optional<Car> optionalCar = this.repo.findById(id);
		Car found = optionalCar.orElseThrow(() -> new EntityNotFoundException());
		return this.mapper.mapToDTO(found);
	}

	public List<Car> findByMake(String make) {
		return this.repo.findByMakeIgnoreCase(make);
	}

	public CarDTO updateCar(Integer id, Car newData) {
		Car existing = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException()); // fetch existing from
																								// db
		existing.setMake(newData.getMake()); // update the values
		existing.setModel(newData.getModel());
		existing.setColour(newData.getColour());

		Car updated = this.repo.save(existing); // save it back to overwrite original

		return this.mapper.mapToDTO(updated);
	}

	public boolean delete(int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	// streams example
	private List<CarDTO> getCars_WITH_STREAMS() {
		return this.repo.findAll().stream().map(car -> this.mapper.mapToDTO(car)).collect(Collectors.toList());
	}

	public List<CarDTO> getCars() {
		List<Car> cars = this.repo.findAll();
		List<CarDTO> dtos = new ArrayList<>();

		CarDTO dto = null;
		for (Car car : cars) {
			dto = this.mapper.mapToDTO(car);
			dtos.add(dto);
		}

		return dtos;
	}

}
