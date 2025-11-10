package com.acid.Project.Catalog.infraestructure.repository;

import com.acid.Project.Catalog.infraestructure.documents.ShirtDocument;
import com.acid.Project.Catalog.domain.Shirt;
import com.acid.Project.Catalog.domain.ShirtRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ShirtRepositoryMongo implements ShirtRepository {

    private final ShirtRepositoryMongoSpring shirtRepositoryMongoSpring;

    public ShirtRepositoryMongo(ShirtRepositoryMongoSpring shirtRepositoryMongoSpring) {
        this.shirtRepositoryMongoSpring = shirtRepositoryMongoSpring;
    }

    @Override
    public void save(Shirt shirt) {
        // hacer clase intermedia record y en el shirtdocument devolver un shirtdocument a partir del record. Shirt ahora tendrá metodo toRecord y no to document
        shirtRepositoryMongoSpring.save(shirt.toDocument());
    }

    @Override
    public List<Shirt> findAll(){
        List<ShirtDocument> list = shirtRepositoryMongoSpring.findAll();
        List<Shirt> shirts = new ArrayList<>();

        for (ShirtDocument shirtDocument : list) {
            shirts.add(Shirt.fromDocument(shirtDocument));
        }

        return shirts;
    }

    @Override
    public void deleteAll(){
        shirtRepositoryMongoSpring.deleteAll();
    }

    @Override
    public Optional<ShirtDocument> findById(Long id){
        return shirtRepositoryMongoSpring.findById(id);
    }
}
