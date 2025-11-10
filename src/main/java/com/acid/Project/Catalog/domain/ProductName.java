package com.acid.Project.Catalog.domain;

public class ProductName {
    private final String name;
// Crear carpeta de error de dominio y hacer throw, extiende de runtime exception
    private ProductName(String name) {
        String v = name == null ? "" : name.trim();
        if (v.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        if (v.length() > 200) throw new IllegalArgumentException("Name too long");
        this.name = v;
    }

    public static ProductName from(String value) { return new ProductName(value); }
    public String value() { return name; }
}
