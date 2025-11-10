package com.acid.Project.Catalog.infraestructure.documents;

import com.acid.Project.Catalog.domain.records.ProductRecord;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document("products")
@Getter
@Setter
public class ProductDocument {
    private Long id;
    private String name;
    private Integer sales;
    private Map<String, Integer> stock;

    public static ProductDocument fromRecord(ProductRecord record) {
        ProductDocument product = new ProductDocument();
        product.setId(record.id());
        product.setName(record.name());
        product.setSales(record.sales());
        product.setStock(record.stock());
        return product;
    }

    public ProductRecord toRecord() {
        return new ProductRecord(this.id, this.name, this.sales, this.stock);
    }
}
