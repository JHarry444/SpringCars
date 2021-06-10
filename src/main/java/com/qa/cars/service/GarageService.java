package com.qa.cars.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.cars.domain.Garage;
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

	public List<Garage> getGarages() {
		return this.repo.findAll();
	}

}
