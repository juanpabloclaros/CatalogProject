package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.domain.errors.EmptyNameException;
import com.acid.Project.Catalog.domain.errors.NameTooLongException;

public class ProductName {
    private final String name;

    private ProductName(String name) {
        String v = name == null ? "" : name.trim();
        if (v.isEmpty()) throw new EmptyNameException();
        if (v.length() > 200) throw new NameTooLongException();
        this.name = v;
    }

    public static ProductName from(String value) { return new ProductName(value); }
    public String value() { return name; }
}
