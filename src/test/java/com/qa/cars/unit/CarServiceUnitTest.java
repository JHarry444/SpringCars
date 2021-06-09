package com.qa.cars.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.cars.domain.Car;
import com.qa.cars.repo.CarRepo;
import com.qa.cars.service.CarService;

@SpringBootTest
public class CarServiceUnitTest {

	@Autowired
	private CarService service;

	@MockBean
	private CarRepo repo;

	@Test
	void testUpdate() {
		// GIVEN
		Integer testId = 1;
		Car testData = new Car("Fiat", "Panda", "white");
		Car existing = new Car(1, null, null, null);
		Car updatedCar = new Car(testId, "Fiat", "Panda", "white");

		// WHEN
		Mockito.when(this.repo.findById(testId)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(updatedCar)).thenReturn(updatedCar);

		// THEN
		assertThat(this.service.updateCar(testId, testData)).isEqualTo(updatedCar);

		Mockito.verify(this.repo, Mockito.times(1)).findById(testId);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedCar);
	}

	@Test
	void testDelete() {
		// GIVEN
		Integer testId = 1;
		boolean exists = false;
		// WHEN
		Mockito.when(this.repo.existsById(testId)).thenReturn(exists);

		// THEN
		assertThat(this.service.delete(testId)).isEqualTo(!exists);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(testId);
	}

}
