package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.domain.records.ProductRecord;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    void deleteAll();
    void save(Product product);
    ProductRecord findById(Long id);
}
