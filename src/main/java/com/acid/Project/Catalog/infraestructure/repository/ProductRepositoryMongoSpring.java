package com.acid.Project.Catalog.infraestructure.repository;

import com.acid.Project.Catalog.infraestructure.documents.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepositoryMongoSpring extends MongoRepository<ProductDocument, Long> {
}
