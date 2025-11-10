package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.domain.errors.NegativeQuantityException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuantityTest {

    @Test
    void create_quantity_when_value_is_valid(){
        assertThat(Quantity.from(5).value()).isEqualTo(5);
    }

    @Test
    void throw_when_quantity_is_negative(){
        assertThatThrownBy(() -> Quantity.from(-5))
                .isInstanceOf(NegativeQuantityException.class);
    }
}
