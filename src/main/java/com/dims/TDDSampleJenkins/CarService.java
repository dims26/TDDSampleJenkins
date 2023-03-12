package com.dims.TDDSampleJenkins;

import com.dims.TDDSampleJenkins.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getCarDetails(String name) {
        Car car = carRepository.findByName(name);

        if (car == null)
            throw new CarNotFoundException();

        return car;
    }
}
