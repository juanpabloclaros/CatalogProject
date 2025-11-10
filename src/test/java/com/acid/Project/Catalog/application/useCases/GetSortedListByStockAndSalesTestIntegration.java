package com.acid.Project.Catalog.application.useCases;

import com.acid.Project.Catalog.domain.Product;
import com.acid.Project.Catalog.domain.ProductRepository;
import com.acid.Project.Catalog.infraestructure.repository.ProductRepositoryMongo;
import com.acid.Project.Catalog.infraestructure.repository.ProductRepositoryMongoSpring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("Test")
public class GetSortedListByStockAndSalesTestIntegration {

    @Autowired
    private ProductRepositoryMongoSpring productRepositoryMongoSpring;
    @Autowired
    private GetSortedListByStockAndSales getSortedListByStockAndSales;

    @BeforeEach
    void clean(){
        ProductRepository productRepository = new ProductRepositoryMongo(productRepositoryMongoSpring);

        productRepository.deleteAll();

        productRepository.save(Product.create(1L,"SHIRT A",100, Map.of("S",4,"M",9,"L",0)));
        productRepository.save(Product.create(2L,"SHIRT B",50, Map.of("S",35,"M",9,"L",9)));
        productRepository.save(Product.create(3L,"SHIRT C",80, Map.of("S",20,"M",2,"L",20)));
    }

    @Test
    void order_by_sales_when_wSales_is_greater_than_wStock() {
        List<Product> productsSorted = getSortedListByStockAndSales.execute(0,1);

        assertThat(productsSorted).extracting(Product::getName).containsExactly("SHIRT A","SHIRT C","SHIRT B");
    }

    @Test
    void order_by_stock_when_wStock_is_greater_than_wSales() {
        List<Product> productsSorted = getSortedListByStockAndSales.execute(1,0);

        assertThat(productsSorted).extracting(Product::getName).containsExactly("SHIRT B","SHIRT C","SHIRT A");
    }

    @Test
    void order_by_name_when_both_zero() {
        List<Product> productsSorted = getSortedListByStockAndSales.execute(0,0);

        assertThat(productsSorted).extracting(Product::getName).containsExactly("SHIRT A","SHIRT B","SHIRT C");
    }
}
