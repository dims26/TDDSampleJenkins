package com.dims.TDDSampleJenkins;

import com.dims.TDDSampleJenkins.domain.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {

    Car findByName(String name);
}
