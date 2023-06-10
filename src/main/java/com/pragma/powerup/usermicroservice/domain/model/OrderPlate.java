package com.pragma.powerup.usermicroservice.domain.model;


public class OrderPlate {

    private Order order;
    private Plate plate;
    private int quantity;

    public OrderPlate() {}

    public OrderPlate(Order order, Plate plate, int quantity) {
        this.order = order;
        this.plate = plate;
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
