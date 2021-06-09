package com.qa.cars.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.cars.domain.Car;
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

	public List<Car> getCars() {
		return this.repo.findAll();
	}

}
