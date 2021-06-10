package com.qa.cars.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.cars.domain.Garage;

@Repository
public interface GarageRepo extends JpaRepository<Garage, Integer> {

}
