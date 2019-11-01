package com.sebastian_daschner.effective_design_principles.systemtest.refactored;

import com.sebastian_daschner.effective_design_principles.systemtest.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

public class SystemTest {

    private CarOrderSystem carOrderSystem;

    @BeforeEach
    void setUp() {
        carOrderSystem = new CarOrderSystem();
    }

    @Test
    void createVerifyBMW() {
        Order order = new Order("BMW", "Red");
        URI orderUri = carOrderSystem.createOrder(order);

        Order loadedOrder = carOrderSystem.getOrder(orderUri);
        assertThat(loadedOrder).isEqualToComparingOnlyGivenFields(order, "brand", "color");

        assertThat(carOrderSystem.getOrders()).contains(orderUri);
    }

    @Test
    void createVerifyMercedes() {
        Order order = new Order("Mercedes", "Silver");
        URI orderUri = carOrderSystem.createOrder(order);

        Order loadedOrder = carOrderSystem.getOrder(orderUri);
        assertThat(loadedOrder).isEqualToComparingOnlyGivenFields(order, "brand", "color");

        assertThat(carOrderSystem.getOrders()).contains(orderUri);
    }

}
