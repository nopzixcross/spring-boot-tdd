package com.example.tdd.bootstrap;

import com.example.tdd.domain.Car;
import com.example.tdd.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CarRepository carRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    public void initData() {
        Car car = new Car("prius", "hybrid");
        Car car2 = new Car("camry", "hybrid");
        Car car3 = new Car("chr", "hybrid");
        carRepository.save(car);
    }
}
