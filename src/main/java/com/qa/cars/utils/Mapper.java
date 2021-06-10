package com.qa.cars.utils;

public interface Mapper<E, D> {

	D mapToDTO(E entity);

	E mapFromDTO(D dto);
}
