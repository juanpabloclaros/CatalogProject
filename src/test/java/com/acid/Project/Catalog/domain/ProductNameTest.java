package com.acid.Project.Catalog.domain;

import com.acid.Project.Catalog.domain.errors.EmptyNameException;
import com.acid.Project.Catalog.domain.errors.NameTooLongException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ProductNameTest {

    @Test
    void create_product_name_when_is_valid() {
        assertThat(ProductName.from("RAISED PRINT T-SHIRT").value()).isEqualTo("RAISED PRINT T-SHIRT");
    }

    @Test
    void throw_with_message_when_name_is_empty() {
        assertThatThrownBy(() -> ProductName.from(" "))
                .isInstanceOf(EmptyNameException.class);
    }

    @Test
    void throw_with_message_when_name_is_too_long() {
        String nameTooLong = "RAISED PRINT T-SHIRT".repeat(100);
        assertThatThrownBy(() -> ProductName.from(nameTooLong))
                .isInstanceOf(NameTooLongException.class);
    }
}
