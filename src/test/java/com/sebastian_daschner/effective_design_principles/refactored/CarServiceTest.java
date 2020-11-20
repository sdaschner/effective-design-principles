package com.sebastian_daschner.effective_design_principles.refactored;

import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

class CarServiceTest {

    private CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService();
        carService.repository = mock(CarRepository.class);
    }

}