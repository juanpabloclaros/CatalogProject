package com.acid.Project.Catalog.domain;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    void get_total_of_product_stock(){
        Product product = Product.create(1L,"V-NECH BASIC SHIRT",100, Map.of("S",4, "M",9,"L",0));
        assertThat(product.totalStock()).isEqualTo(13);
    }
}
