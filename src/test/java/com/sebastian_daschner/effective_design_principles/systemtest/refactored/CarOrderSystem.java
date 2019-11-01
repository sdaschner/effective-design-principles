package com.sebastian_daschner.effective_design_principles.systemtest.refactored;

import com.sebastian_daschner.effective_design_principles.systemtest.Order;
import com.sebastian_daschner.effective_design_principles.systemtest.refactored.RequestJsonBuilder;

import javax.json.JsonArray;
import javax.json.JsonObject;
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

public class CarOrderSystem {

    private final Client client;
    private final WebTarget ordersTarget;
    private final RequestJsonBuilder jsonBuilder;

    public CarOrderSystem() {
        client = ClientBuilder.newClient();
        ordersTarget = client.target(buildUri());
        jsonBuilder = new RequestJsonBuilder();
    }

    private URI buildUri() {
        String host = System.getProperty("car-shop.test.host", "localhost");
        String port = System.getProperty("car-shop.test.port", "8001");
        return UriBuilder.fromUri("http://{host}:{port}/car-shop/resources/orders")
                .build(host, port);
    }

    public List<URI> getOrders() {
        return ordersTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .get(JsonArray.class).getValuesAs(JsonObject.class).stream()
                .map(o -> o.getString("_self"))
                .map(URI::create)
                .collect(Collectors.toList());
    }

    public URI createOrder(Order order) {
        Response response = sendRequest(order);
        verifySuccess(response);
        return response.getLocation();
    }

    private Response sendRequest(Order order) {
        JsonObject requestBody = jsonBuilder.forOrder(order);
        return ordersTarget.request().post(Entity.json(requestBody));
    }

    private void verifySuccess(Response response) {
        if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL)
            throw new AssertionError("Status was not successful: " + response.getStatus());
    }

    public Order getOrder(URI orderUri) {
        return client.target(orderUri)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Order.class);
    }

}
