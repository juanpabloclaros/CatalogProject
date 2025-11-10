package com.acid.Project.Catalog.infraestructure.repository;

import com.acid.Project.Catalog.infraestructure.documents.ProductDocument;
import com.acid.Project.Catalog.domain.Product;
import com.acid.Project.Catalog.domain.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryMongo implements ProductRepository {

    private final ProductRepositoryMongoSpring productRepositoryMongoSpring;

    public ProductRepositoryMongo(ProductRepositoryMongoSpring productRepositoryMongoSpring) {
        this.productRepositoryMongoSpring = productRepositoryMongoSpring;
    }

    @Override
    public void save(Product product) {
        // hacer clase intermedia record y en el shirtdocument devolver un shirtdocument a partir del record. Shirt ahora tendrá metodo toRecord y no to document
        productRepositoryMongoSpring.save(product.toDocument());
    }

    @Override
    public List<Product> findAll(){
        List<ProductDocument> list = productRepositoryMongoSpring.findAll();
        List<Product> products = new ArrayList<>();

        for (ProductDocument productDocument : list) {
            products.add(Product.fromDocument(productDocument));
        }

        return products;
    }

    @Override
    public void deleteAll(){
        productRepositoryMongoSpring.deleteAll();
    }

    @Override
    public Optional<ProductDocument> findById(Long id){
        return productRepositoryMongoSpring.findById(id);
    }
}
