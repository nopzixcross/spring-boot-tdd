package com.example.tdd.repository;

import com.example.tdd.domain.Car;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class CarRepositoryTest {

    @Autowired
    private CarRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findByName_ReturnsCar() throws Exception {
        Car savedCar = testEntityManager.persistFlushFind(new Car("prius", "hybrid"));
        Car car = this.repository.findByName("prius");
        assertThat(car.getName()).isEqualTo(savedCar.getName());
        assertThat(car.getType()).isEqualTo(savedCar.getType());
    }
}