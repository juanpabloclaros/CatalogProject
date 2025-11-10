package com.acid.Project.Catalog.domain;

public class ProductId {
    private final Long id;

    private ProductId(Long id){
        if(id == null) throw new IllegalArgumentException("id cannot be null");
        if(id < 0) throw new IllegalArgumentException("id cannot be negative");

        this.id = id;
    }

    public static ProductId fromId(Long id){
        return new ProductId(id);
    }

    public Long value(){
        return id;
    }
}
