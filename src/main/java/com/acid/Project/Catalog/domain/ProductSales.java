package com.acid.Project.Catalog.domain;

public class ProductSales {
    private final int amount;

    private ProductSales(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Sales cannot be negative");
        this.amount = amount;
    }

    public static ProductSales from(int value){ return new ProductSales(value); }
    public int value(){ return amount; }
}
