package com.acid.Project.Catalog.domain.errors;

public class EmptyNameException extends RuntimeException{
    public EmptyNameException() {
        super("Name cannot be empty");
    }
}
