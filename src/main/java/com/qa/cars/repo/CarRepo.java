package com.qa.cars.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.cars.domain.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

	List<Car> findByMakeIgnoreCase(String make);

}
