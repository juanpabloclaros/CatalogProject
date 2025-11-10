package com.acid.Project.Catalog.domain.errors;

public class NegativeWeightStockException extends RuntimeException{
    public NegativeWeightStockException(){
        super("Weight of stock cannot be negative");
    }
}
