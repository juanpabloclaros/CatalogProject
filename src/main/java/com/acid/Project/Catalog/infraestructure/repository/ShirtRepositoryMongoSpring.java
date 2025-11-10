package com.acid.Project.Catalog.infraestructure.repository;

import com.acid.Project.Catalog.infraestructure.documents.ShirtDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShirtRepositoryMongoSpring  extends MongoRepository<ShirtDocument, Long> {
}
