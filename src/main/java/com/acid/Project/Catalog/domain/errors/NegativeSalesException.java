package com.acid.Project.Catalog.domain.errors;

public class NegativeSalesException extends RuntimeException {
    public NegativeSalesException() {
        super("Sales cannot be negative");
    }
}
