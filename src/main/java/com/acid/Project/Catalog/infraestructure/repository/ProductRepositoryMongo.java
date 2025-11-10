package com.acid.Project.Catalog.infraestructure.repository;

import com.acid.Project.Catalog.domain.records.ProductRecord;
import com.acid.Project.Catalog.infraestructure.documents.ProductDocument;
import com.acid.Project.Catalog.domain.Product;
import com.acid.Project.Catalog.domain.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
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
        productRepositoryMongoSpring.save(ProductDocument.fromRecord(product.toRecord()));
    }

    @Override
    public List<Product> findAll(){
        List<ProductDocument> list = productRepositoryMongoSpring.findAll();
        List<Product> products = new ArrayList<>();

        for (ProductDocument productDocument : list) {
            products.add(Product.fromRecord(productDocument.toRecord()));
        }

        return products;
    }

    @Override
    public void deleteAll(){
        productRepositoryMongoSpring.deleteAll();
    }

    @Override
    public ProductRecord findById(Long id){
        ProductRecord productRecord = new ProductRecord(-1L, "default", 0, new HashMap<>());
        Optional<ProductDocument> productDocument = productRepositoryMongoSpring.findById(id);

        return productDocument
                .map(document -> new ProductRecord(document.getId(), document.getName(), document.getSales(), document.getStock()))
                .orElse(productRecord);
    }
}
