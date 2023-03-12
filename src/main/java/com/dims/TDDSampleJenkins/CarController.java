package com.dims.TDDSampleJenkins;

import com.dims.TDDSampleJenkins.domain.Car;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars/{name}")
    private Car getCar(@PathVariable String name){
        return carService.getCarDetails(name);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    // Handles any CarNotFoundException generated through this Controller's dependencies and returns the NOT_FOUND response
    private void carNotFoundHandler(CarNotFoundException ex){}
}
