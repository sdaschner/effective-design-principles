package com.sebastian_daschner.effective_design_principles.systemtest;

public class Order {

    private String id;
    private String brand;
    private String color;
    private String status;

    public Order() {
    }

    public Order(String brand, String color) {
        this.brand = brand;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
