package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.domain.errors.NegativeQuantityException;

public class Quantity {
    private final int quantity;

    private Quantity(int quantity){
        if (quantity < 0) throw new NegativeQuantityException();
        this.quantity = quantity;
    }

    public static Quantity from(int quantity){ return new Quantity(quantity); }
    public int value(){ return quantity; }
}
