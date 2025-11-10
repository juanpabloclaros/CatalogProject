package com.acid.Project.Catalog.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ShirtNameTest {

    @Test
    void create_shirt_name_when_is_valid() {
        assertThat(ShirtName.from("RAISED PRINT T-SHIRT").value()).isEqualTo("RAISED PRINT T-SHIRT");
    }

    @Test
    void throw_with_message_when_name_is_empty() {
        assertThatThrownBy(() -> ShirtName.from(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name cannot be empty");
    }

    @Test
    void throw_with_message_when_name_is_too_long() {
        String nameTooLong = "RAISED PRINT T-SHIRT".repeat(100);
        assertThatThrownBy(() -> ShirtName.from(nameTooLong))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name too long");
    }
}
