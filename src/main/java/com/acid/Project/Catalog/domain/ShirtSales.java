package com.acid.Project.Catalog.domain;

public class ShirtSales {
    private final int amount;

    private ShirtSales(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Sales cannot be negative");
        this.amount = amount;
    }

    public static ShirtSales from(int value){ return new ShirtSales(value); }
    public int value(){ return amount; }
}
