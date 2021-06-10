package com.qa.cars.utils;

import com.qa.cars.domain.Car;
import com.qa.cars.dto.CarDTO;

public class CarMapper implements Mapper<Car, CarDTO> {

	@Override
	public CarDTO mapToDTO(Car car) {
		CarDTO dto = new CarDTO();

		dto.setColour(car.getColour());
		dto.setId(car.getId());
		dto.setMake(car.getMake());
		dto.setModel(car.getModel());

		return dto;
	}

	@Override
	public Car mapFromDTO(CarDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
}
