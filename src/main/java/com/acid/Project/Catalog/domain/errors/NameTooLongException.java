package com.acid.Project.Catalog.domain.errors;

public class NameTooLongException extends RuntimeException{
    public NameTooLongException() {
        super("Name too long");
    }
}
