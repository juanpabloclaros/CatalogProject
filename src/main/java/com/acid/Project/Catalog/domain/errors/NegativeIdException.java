package com.acid.Project.Catalog.domain.errors;

public class NegativeIdException extends RuntimeException {
    public NegativeIdException() {
        super("Id cannot be negative");
    }
}
