package com.acid.Project.Catalog.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ShirtSalesTest {

    @Test
    void create_shirt_sales_when_amount_is_positive() {
        assertThat(ShirtSales.from(100).value()).isEqualTo(100);
    }

    @Test
    void throw_with_message_when_sales_is_negative() {
        assertThatThrownBy(() -> ShirtSales.from(-100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Sales cannot be negative");
    }
}
