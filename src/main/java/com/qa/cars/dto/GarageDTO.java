package com.qa.cars.dto;

import java.util.List;

public class GarageDTO {

	private Integer id;

	private String name;

	private List<CarDTO> cars;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CarDTO> getCars() {
		return cars;
	}

	public void setCars(List<CarDTO> cars) {
		this.cars = cars;
	}

}
