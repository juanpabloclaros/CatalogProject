package com.acid.Project.Catalog.infraestructure.repository;

import com.acid.Project.Catalog.domain.Product;
import com.acid.Project.Catalog.domain.ProductRepository;
import com.acid.Project.Catalog.domain.records.ProductRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryMemory implements ProductRepository {
    private List<Product> productList;

    public ProductRepositoryMemory(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public List<Product> findAll(){
        return productList;
    }
    public void deleteAll(){
        productList.clear();
    }
    public void save(Product product){
        productList.add(product);
    }
    public ProductRecord findById(Long id){
        ProductRecord productRecord = new ProductRecord(-1L,"Default",0,new HashMap<>());
        Optional<Product> product = productList.stream().filter(p -> p.getProductId().equals(id)).findFirst();

        if (product.isPresent()){
            return product.get().toRecord();
        }

        return productRecord;
    }
}
