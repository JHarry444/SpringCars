package com.qa.cars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.cars.domain.Car;
import com.qa.cars.domain.Garage;
import com.qa.cars.dto.CarDTO;
import com.qa.cars.dto.GarageDTO;
import com.qa.cars.repo.GarageRepo;

@Service
public class GarageService {

	private GarageRepo repo;

	@Autowired
	public GarageService(GarageRepo repo) {
		super();
		this.repo = repo;
	}

	public Garage createGarage(Garage garage) {
		return this.repo.save(garage);
	}

	public Garage findGarage(Integer id) {
		Optional<Garage> optionalGarage = this.repo.findById(id);
		return optionalGarage.orElseThrow(() -> new EntityNotFoundException());
	}

	public Garage updateGarage(Integer id, Garage newData) {
		Garage existing = this.findGarage(id); // fetch existing from db

		existing.setName(newData.getName()); // update the values

		Garage updated = this.repo.save(existing); // save it back to overwrite original

		return updated;
	}

	public boolean delete(int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	private GarageDTO mapToDTO(Garage garage) {
		GarageDTO dto = new GarageDTO();
		dto.setId(garage.getId());
		dto.setName(garage.getName());
		List<CarDTO> cars = new ArrayList<>();
		for (Car car : garage.getCars()) {
			cars.add(this.mapToDTO(car));
		}
		dto.setCars(cars);
		return dto;
	}

	private CarDTO mapToDTO(Car car) {
		CarDTO dto = new CarDTO();

		dto.setColour(car.getColour());
		dto.setId(car.getId());
		dto.setMake(car.getMake());
		dto.setModel(car.getModel());

		return dto;
	}

	public List<GarageDTO> getGarages() {
		List<Garage> garages = this.repo.findAll();

		List<GarageDTO> dtos = new ArrayList<>();

		for (Garage g : garages) {
			GarageDTO dto = this.mapToDTO(g);
			dtos.add(dto);
		}
		return dtos;
	}

}
