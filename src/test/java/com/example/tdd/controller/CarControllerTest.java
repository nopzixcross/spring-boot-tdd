package com.example.tdd.controller;

import com.example.tdd.CarNotFoundException;
import com.example.tdd.controller.CarController;
import com.example.tdd.service.CarService;
import com.example.tdd.domain.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CarService carService;

    @Test
    public void getCar_ShoudReturnCar() throws Exception {
        given(carService.getCarDetails(anyString())).willReturn(new Car("prius", "hybrid"));
        mockMvc.perform(get("/cars/prius")).andExpect(status().isOk()).andExpect(jsonPath("name").value("prius")).andExpect(jsonPath("type").value("hybrid"));
    }

    @Test
    public void getCar_notFound() throws Exception {
        given(carService.getCarDetails(anyString())).willThrow(new CarNotFoundException());
        mockMvc.perform(get("/cars/prius")).andExpect(status().isNotFound());
    }
}
