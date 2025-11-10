package com.acid.Project.Catalog.domain;

public class ShirtName {
    private final String name;
// Crear carpeta de error de dominio y hacer throw, extiende de runtime exception
    private ShirtName(String name) {
        String v = name == null ? "" : name.trim();
        if (v.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        if (v.length() > 200) throw new IllegalArgumentException("Name too long");
        this.name = v;
    }

    public static ShirtName from(String value) { return new ShirtName(value); }
    public String value() { return name; }
}
