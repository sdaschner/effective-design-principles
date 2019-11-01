package com.sebastian_daschner.effective_design_principles.systemtest.refactored;

import com.sebastian_daschner.effective_design_principles.systemtest.Order;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

class RequestJsonBuilder {

    JsonObject forOrder(Order order) {
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
