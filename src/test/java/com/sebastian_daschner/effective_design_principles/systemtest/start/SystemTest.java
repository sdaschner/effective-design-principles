package com.sebastian_daschner.effective_design_principles.systemtest.start;

import com.sebastian_daschner.effective_design_principles.systemtest.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SystemTest {

    private Client client;
    private WebTarget ordersTarget;

    @BeforeEach
    void setUp() {
        client = ClientBuilder.newClient();
        ordersTarget = client.target(buildUri());
    }

    private URI buildUri() {
        String host = System.getProperty("car-shop.test.host", "localhost");
        String port = System.getProperty("car-shop.test.port", "8080");
        return UriBuilder.fromUri("http://{host}:{port}/car-shop/resources/orders")
                .build(host, port);
    }

    @Test
    void createVerifyBMW() {
        List<URI> originalOrders = ordersTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .get(JsonArray.class).getValuesAs(JsonObject.class).stream()
                .map(o -> o.getString("_self"))
                .map(URI::create)
                .collect(Collectors.toList());

        Order order = new Order("BMW", "Red");
        JsonObject requestBody = createJson(order);

        Response response = ordersTarget.request().post(Entity.json(requestBody));

        if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL)
            throw new AssertionError("Status was not successful: " + response.getStatus());

        URI orderUri = response.getLocation();

        Order loadedOrder = client.target(orderUri)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Order.class);

        assertThat(loadedOrder).isEqualToComparingOnlyGivenFields(order, "brand", "color");

        List<URI> updatedOrders = ordersTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .get(JsonArray.class).getValuesAs(JsonObject.class).stream()
                .map(o -> o.getString("_self"))
                .map(URI::create)
                .collect(Collectors.toList());

        assertThat(updatedOrders).hasSize(originalOrders.size() + 1);
    }

    JsonObject createJson(Order order) {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        if (order.getBrand() != null)
            builder.add("brand", order.getBrand());
        else
            builder.addNull("brand");
        if (order.getColor() != null)
            builder.add("color", order.getColor());
        else
            builder.addNull("color");

        return builder.build();
    }

}
