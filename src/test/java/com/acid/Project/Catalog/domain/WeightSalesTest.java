package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.domain.errors.NegativeWeightSalesException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WeightSalesTest {

    @Test
    void create_weight_sales_when_values_is_valid(){
        assertThat(WeightSales.from(0.0).value()).isEqualTo(0.0);
    }

    @Test
    void throw_when_weight_sales_is_not_valid(){
        assertThatThrownBy(() -> WeightSales.from(-1.0))
                .isInstanceOf(NegativeWeightSalesException.class);
    }
}
