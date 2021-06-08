package com.qa.cars.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.cars.domain.Car;

@Service
public class CarService {

	private List<Car> cars = new ArrayList<>();

	public String createCar(Car car) {
		this.cars.add(car);
		System.out.println(this.cars);
		return this.cars.toString();
	}

	public String delete(int id) {
		this.cars.remove(id);
		return this.cars.toString();
	}

	public List<Car> getCars() {
		return this.cars;
	}

}
