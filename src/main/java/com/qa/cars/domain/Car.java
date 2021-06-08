package com.qa.cars.domain;

public class Car {

	private String make;

	private String model;

	private String colour;

	public Car() {
	}

	public Car(String make, String model, String colour) {
		super();
		this.make = make;
		this.model = model;
		this.colour = colour;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	@Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", colour=" + colour + "]";
	}

}
