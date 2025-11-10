package com.acid.Project.Catalog.domain;


import com.acid.Project.Catalog.domain.records.ProductRecord;

import java.util.EnumMap;
import java.util.Map;

public class Product {
    private ProductId id;
    private ProductName name;
    private ProductSales sales;
    private ProductStock stock;

    private Product(ProductId id, ProductName name, ProductSales sales, ProductStock stock) {
        this.id = id;
        this.name = name;
        this.sales = sales;
        this.stock = stock;
    }

    public static Product create(Long id, String name, int sales, Map<String,Integer> stock) {
        // Pasar el stock a Map<Size, Integer> y ahorrarme el switch case y el rellenado de 0
        ProductId productId = ProductId.from(id);
        ProductName productName = ProductName.from(name);
        ProductSales productSales = ProductSales.from(sales);
        Map<Size,Quantity> stockAux = new EnumMap<>(Size.class);

        stock.forEach((s,qty) ->{
            switch (s) {
                case "S" -> stockAux.put(Size.S, Quantity.from(qty));
                case "M" -> stockAux.put(Size.M, Quantity.from(qty));
                case "L" -> stockAux.put(Size.L, Quantity.from(qty));
            }
        });

        for (Size sz : new Size[]{Size.S, Size.M, Size.L}) {
            stockAux.putIfAbsent(sz, Quantity.from(0));
        }

        ProductStock newStock = ProductStock.from(stockAux);

        return new Product(productId, productName, productSales, newStock);
    }

    public static Product fromRecord(ProductRecord productRecord){
        return Product.create(
                productRecord.id(),
                productRecord.name(),
                productRecord.sales() == null ? 0 : productRecord.sales(),
                productRecord.stock()
        );
    }

    public ProductRecord toRecord(){
        return new ProductRecord(id.value(), name.value(), sales.value(), stock.toRecord());
    }

    public int totalStock(){
        return stock.getTotal();
    }

    public String getName(){
        return name.value();
    }

    public int getSales(){
        return sales.value();
    }
}
