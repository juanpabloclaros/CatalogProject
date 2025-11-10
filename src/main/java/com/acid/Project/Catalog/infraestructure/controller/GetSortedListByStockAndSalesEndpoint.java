package com.acid.Project.Catalog.infraestructure.controller;

import com.acid.Project.Catalog.domain.ProductRepository;
import com.acid.Project.Catalog.domain.records.ProductRecord;
import com.acid.Project.Catalog.infraestructure.dto.ProductResponse;
import com.acid.Project.Catalog.domain.Product;
import com.acid.Project.Catalog.application.useCases.GetSortedListByStockAndSales;
import com.acid.Project.Catalog.infraestructure.repository.ProductRepositoryMongo;
import com.acid.Project.Catalog.infraestructure.repository.ProductRepositoryMongoSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products/weighted")
public class GetSortedListByStockAndSalesEndpoint {

    @Autowired
    private ProductRepositoryMongo  productRepositoryMongo;

    @GetMapping()
    public List<ProductResponse> execute(
            @RequestParam(name = "wStock") double wStock,
            @RequestParam(name = "wSales") double wSales
    ) {
        GetSortedListByStockAndSales getSortedListByStockAndSales = new GetSortedListByStockAndSales(productRepositoryMongo);

        return getSortedListByStockAndSales.execute(wStock, wSales).stream().map(this::toResponse).toList();
    }

    private ProductResponse toResponse(Product product){
        ProductRecord productRecord = product.toRecord();

        return new ProductResponse(productRecord.id(), productRecord.name(), productRecord.sales(),  productRecord.stock());
    }
}
