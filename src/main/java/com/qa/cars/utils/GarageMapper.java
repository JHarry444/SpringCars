package com.qa.cars.utils;

import java.util.ArrayList;
import java.util.List;

import com.qa.cars.domain.Car;
import com.qa.cars.domain.Garage;
import com.qa.cars.dto.CarDTO;
import com.qa.cars.dto.GarageDTO;

public class GarageMapper implements Mapper<Garage, GarageDTO> {

	private CarMapper carMapper;

	public GarageMapper(CarMapper carMapper) {
		super();
		this.carMapper = carMapper;
	}

	@Override
	public GarageDTO mapToDTO(Garage entity) {
		GarageDTO dto = new GarageDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		List<CarDTO> cars = new ArrayList<>();
		for (Car car : entity.getCars()) {
			cars.add(this.carMapper.mapToDTO(car));
		}
		dto.setCars(cars);
		return dto;
	}

	@Override
	public Garage mapFromDTO(GarageDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
