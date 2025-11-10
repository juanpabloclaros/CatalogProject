package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.domain.errors.NegativeWeightSalesException;

public class WeightSales {
    private final double weight;

    private WeightSales(double weight) {
        if (weight < 0) throw new NegativeWeightSalesException();
        this.weight = weight;
    }

    public static WeightSales from(double weight) {
        return new WeightSales(weight);
    }

    public double value(){
        return weight;
    }

    public boolean isZero() {
        return weight == 0;
    }
}
