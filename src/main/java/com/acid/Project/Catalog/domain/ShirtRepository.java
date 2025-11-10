package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.infraestructure.documents.ShirtDocument;

import java.util.List;
import java.util.Optional;

public interface ShirtRepository {
    public List<Shirt> findAll();
    void deleteAll();
    void save(Shirt shirt);
    Optional<ShirtDocument> findById(Long id);
}
