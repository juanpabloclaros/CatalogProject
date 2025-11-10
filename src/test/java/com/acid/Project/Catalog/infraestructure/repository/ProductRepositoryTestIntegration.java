package com.acid.Project.Catalog.infraestructure.repository;

import com.acid.Project.Catalog.infraestructure.documents.ProductDocument;
import com.acid.Project.Catalog.domain.Product;
import com.acid.Project.Catalog.domain.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ProductRepositoryTestIntegration {

    @Autowired
    ProductRepositoryMongoSpring productRepositoryMongoSpring;

    @BeforeEach
    void cleanData(){
        productRepositoryMongoSpring.deleteAll();
    }

    @Test
    void save_product(){
        ProductRepository productRepository = new ProductRepositoryMongo(productRepositoryMongoSpring);

        productRepository.save(Product.create(1L,"V-NECH BASIC SHIRT",100, Map.of("S",4,"M",9,"L",0)));
        Optional<ProductDocument> productDocumentFound = productRepository.findById(1L);

        assertThat(productDocumentFound.isPresent()).isTrue();
    }

    @Test
    void retrieves_all_products_saved(){
        ProductRepository productRepository = new ProductRepositoryMongo(productRepositoryMongoSpring);

        productRepository.save(Product.create(1L,"V-NECH BASIC SHIRT",100, Map.of("S",4,"M",9,"L",0)));
        productRepository.save(Product.create(2L,"CONTRASTING FABRIC T-SHIRT",50, Map.of("S",35,"M",9,"L",9)));
        productRepository.save(Product.create(3L,"RAISED PRINT T-SHIRT",80, Map.of("S",20,"M",2,"L",20)));

        assertThat(productRepository.findAll().size()).isEqualTo(3);
    }
}
