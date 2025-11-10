package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.domain.errors.NegativeIdException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductIdTest {

    @Test
    void create_product_id_when_is_valid(){
        assertThat(ProductId.from(1L).value()).isEqualTo(1L);
    }

    @Test
    void throw_with_message_when_quantity_is_negative(){
        assertThatThrownBy(() -> ProductId.from((long) -5))
                .isInstanceOf(NegativeIdException.class);
    }
}
