package com.acid.Project.Catalog.infraestructure.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document("shirts")
@Getter
@Setter
public class ShirtDocument {
    private Long id;
    private String name;
    private Integer sales;
    // poner el enum y me ahorro los parseos entre string
    private Map<String, Integer> stock;
}
