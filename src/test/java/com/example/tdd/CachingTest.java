package com.example.tdd;

import com.example.tdd.domain.Car;
import com.example.tdd.repository.CarRepository;
import com.example.tdd.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CachingTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Test
    public void caching() throws Exception {
        //arrange
        given(carRepository.findByName(anyString())).willReturn(new Car("prius", "hybrid"));

        //act
        carService.getCarDetails("prius");
        carService.getCarDetails("prius");

        //assert
        verify(carRepository, times(1)).findByName("prius");
    }
}
