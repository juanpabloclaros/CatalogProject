package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.domain.errors.NegativeWeightStockException;

public class WeightStock {
    private final double weight;

    private WeightStock(double weight) {
        if (weight < 0) throw new NegativeWeightStockException();
        this.weight = weight;
    }

    public static WeightStock from(double weight) {
        return new WeightStock(weight);
    }

    public double value() {
        return weight;
    }

    public boolean isZero() {
        return weight == 0;
    }
}
