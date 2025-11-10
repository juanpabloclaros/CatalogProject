package com.acid.Project.Catalog.domain.records;

import java.util.Map;

public record ProductRecord(
        Long id,
        String name,
        Integer sales,
        Map<String, Integer> stock) {
}
