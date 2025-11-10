package com.acid.Project.Catalog.infraestructure.dto;

import java.util.Map;

public record ProductResponse(
        Long id,
        String name,
        Integer sales,
        Map<String, Integer> stock
) {
}
