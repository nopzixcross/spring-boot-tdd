package com.example.tdd.service;

import com.example.tdd.CarNotFoundException;
import com.example.tdd.domain.Car;
import com.example.tdd.repository.CarRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Cacheable("cars")
    public Car getCarDetails(String name) {
        Car car = carRepository.findByName(name);
        if (car == null) {
            throw new CarNotFoundException();
        }
        return car;
    }
}
