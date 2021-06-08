package com.qa.cars.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity // marks a class as representing a table in the db
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Integer id;

	@Column(name = "bloop", unique = true)
	private String make;

	private String model;

	private String colour;

	@ManyToOne
	private Garage garage;

	public Car() {
		// REQUIRED
	}

	public Car(String make, String model, String colour) {
		super();
		this.make = make;
		this.model = model;
		this.colour = colour;
	}

	// REQUIRED

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Garage getGarage() {
		return garage;
	}

	public void setGarage(Garage garage) {
		this.garage = garage;
	}

	@Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", colour=" + colour + "]";
	}

}
