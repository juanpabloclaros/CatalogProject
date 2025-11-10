package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.infraestructure.documents.ProductDocument;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    public List<Product> findAll();
    void deleteAll();
    void save(Product product);
    Optional<ProductDocument> findById(Long id);
}
