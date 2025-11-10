package com.acid.Project.Catalog.domain.errors;

public class NegativeWeightSalesException extends RuntimeException {
    public NegativeWeightSalesException() {
        super("Weight of sales cannot be negative");
    }
}
