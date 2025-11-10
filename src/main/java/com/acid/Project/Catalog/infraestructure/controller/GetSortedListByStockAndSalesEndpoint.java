package com.acid.Project.Catalog.infraestructure.controller;

import com.acid.Project.Catalog.domain.records.ProductRecord;
import com.acid.Project.Catalog.infraestructure.dto.ProductResponse;
import com.acid.Project.Catalog.domain.Product;
import com.acid.Project.Catalog.application.useCases.GetSortedListByStockAndSales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products/weighted")
public class GetSortedListByStockAndSalesEndpoint {
//    http://localhost:8080/api/products/weighted?wStock=0.2&wSales=1
    @Autowired
    private GetSortedListByStockAndSales getSortedListByStockAndSales;

    @GetMapping()
    public List<ProductResponse> execute(
            @RequestParam(name = "wStock") double wStock,
            @RequestParam(name = "wSales") double wSales
    ) {
        return getSortedListByStockAndSales.execute(wStock, wSales).stream().map(this::toResponse).toList();
    }

    private ProductResponse toResponse(Product product){
        ProductRecord productRecord = product.toRecord();

        return new ProductResponse(productRecord.id(), productRecord.name(), productRecord.sales(),  productRecord.stock());
    }
}
