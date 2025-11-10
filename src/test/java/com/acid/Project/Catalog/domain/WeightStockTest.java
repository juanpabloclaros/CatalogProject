package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.domain.errors.NegativeWeightStockException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WeightStockTest {

    @Test
    void create_weight_stock_when_value_is_valid(){
        assertThat(WeightStock.from(1.0).value()).isEqualTo(1.0);
    }

    @Test
    void throw_when_weight_stock_is_not_valid(){
        assertThatThrownBy(() -> WeightStock.from(-1.0))
                .isInstanceOf(NegativeWeightStockException.class);
    }
}
