package com.acid.Project.Catalog.domain;

import java.util.EnumMap;
import java.util.Map;

public class ShirtStock {
    private final EnumMap<Size, Quantity> items;

    private ShirtStock(EnumMap<Size, Quantity> items){
        this.items = new EnumMap<>(items);
    }

    public static ShirtStock empty(){ return new ShirtStock(new EnumMap<>(Size.class)); }

    public static ShirtStock from(Map<Size, Quantity> source){
        EnumMap<Size, Quantity> map = new EnumMap<>(Size.class);
        if (source != null) source.forEach((k,v) -> map.put(k, v==null? Quantity.from(0): v));
        return new ShirtStock(map);
    }
// quitar estos dos funciones que no se usan
    public Quantity get(Size size){ return items.getOrDefault(size, Quantity.from(0)); }

    public ShirtStock with(Size size, Quantity qty){
        EnumMap<Size, Quantity> copy = new EnumMap<>(items);
        copy.put(size, qty);
        return new ShirtStock(copy);
    }

    public Map<Size, Quantity> asMap(){ return Map.copyOf(items); }
}
