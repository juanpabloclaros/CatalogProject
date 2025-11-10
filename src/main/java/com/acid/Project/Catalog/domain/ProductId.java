package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.domain.errors.NegativeIdException;

public class ProductId {
    private final Long id;

    private ProductId(Long id){
        if(id < 0) throw new NegativeIdException();

        this.id = id;
    }

    public static ProductId from(Long id){
        return new ProductId(id);
    }

    public Long value(){
        return id;
    }
}
