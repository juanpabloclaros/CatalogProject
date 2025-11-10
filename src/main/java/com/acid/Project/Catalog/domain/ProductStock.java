package com.acid.Project.Catalog.domain;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ProductStock {
    private final EnumMap<Size, Quantity> items;

    private ProductStock(EnumMap<Size, Quantity> items){
        this.items = new EnumMap<>(items);
    }

    public static ProductStock from(Map<Size, Quantity> source){
        EnumMap<Size, Quantity> map = new EnumMap<>(Size.class);
        if (source != null) source.forEach((k,v) -> map.put(k, v == null ? Quantity.from(0) : v));

        return new ProductStock(map);
    }

    public int getTotal(){
        return items.values().stream().mapToInt(Quantity::value).sum();
    }

    public Map<String, Integer> toRecord(){
        Map<String, Integer> map = new HashMap<>();
        items.forEach((size, qty)-> map.put(size.name(), qty.value()));

        return map;
    }
}
