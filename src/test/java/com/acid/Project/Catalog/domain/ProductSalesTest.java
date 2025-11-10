package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.domain.errors.NegativeSalesException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductSalesTest {

    @Test
    void create_product_sales_when_amount_is_positive() {
        assertThat(ProductSales.from(100).value()).isEqualTo(100);
    }

    @Test
    void throw_with_message_when_sales_is_negative() {
        assertThatThrownBy(() -> ProductSales.from(-100))
                .isInstanceOf(NegativeSalesException.class);
    }
}
