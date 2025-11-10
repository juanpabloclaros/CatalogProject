package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.domain.errors.NegativeSalesException;

public class ProductSales {
    private final int amount;

    private ProductSales(int amount) {
        if (amount < 0) throw new NegativeSalesException();
        this.amount = amount;
    }

    public static ProductSales from(int value){ return new ProductSales(value); }
    public int value(){ return amount; }
}
