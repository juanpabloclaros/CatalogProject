package com.acid.Project.Catalog.domain;

public class Quantity {
    private final int quantity;

    private Quantity(int quantity){
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        this.quantity = quantity;
    }

    public static Quantity from(int quantity){ return new Quantity(quantity); }
    public int value(){ return quantity; }
}
