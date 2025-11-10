package com.acid.Project.Catalog.domain.errors;

public class NegativeQuantityException extends RuntimeException {
    public NegativeQuantityException() {
        super("Quantity cannot be negative");
    }
}
