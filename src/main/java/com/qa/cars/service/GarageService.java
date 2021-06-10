package com.qa.cars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.qa.cars.domain.Garage;
import com.qa.cars.dto.GarageDTO;
import com.qa.cars.repo.GarageRepo;
import com.qa.cars.utils.GarageMapper;

@Service
public class GarageService {

	private GarageRepo repo;

	private GarageMapper mapper;

	public GarageService(GarageRepo repo, GarageMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public GarageDTO createGarage(Garage garage) {
		Garage saved = this.repo.save(garage);
		return this.mapper.mapToDTO(saved);
	}

	public GarageDTO findGarage(Integer id) {
		Optional<Garage> optionalGarage = this.repo.findById(id);
		Garage found = optionalGarage.orElseThrow(() -> new EntityNotFoundException());
		return this.mapper.mapToDTO(found);
	}

	public GarageDTO updateGarage(Integer id, Garage newData) {
		Garage existing = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException()); // fetch existing
																									// from db

		existing.setName(newData.getName()); // update the values

		Garage updated = this.repo.save(existing); // save it back to overwrite original

		return this.mapper.mapToDTO(updated);
	}

	public boolean delete(int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public List<GarageDTO> getGarages() {
		List<Garage> garages = this.repo.findAll();

		List<GarageDTO> dtos = new ArrayList<>();

		for (Garage g : garages) {
			GarageDTO dto = this.mapper.mapToDTO(g);
			dtos.add(dto);
		}
		return dtos;
	}

}
