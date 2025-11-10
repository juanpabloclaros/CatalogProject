package com.acid.Project.Catalog.domain;


import com.acid.Project.Catalog.infraestructure.documents.ProductDocument;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
// TODO: CAMBIAR SHIRT POR PRODUCTO EN TODOS SITIOS
public class Product {
    private Long id; // ProductId y verificar que no es negativo
    private ProductName name;
    private ProductSales sales;
    private ProductStock stock;

    private Product(Long id, ProductName name, ProductSales sales, ProductStock stock) {
        this.id = id;
        this.name = name;
        this.sales = sales;
        this.stock = stock;
    }

    public static Product create(Long id, String name, int sales, Map<String,Integer> stock) {
        // Pasar el stock a Map<Size, Integer> y ahorrarme el switch case y el rellenado de 0
        ProductName sName = ProductName.from(name);
        ProductSales sSales = ProductSales.from(sales);
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

        return new Product(id, sName, sSales, newStock);
    }

    public static Product fromDocument(ProductDocument doc){
        return Product.create(
                doc.getId(),
                doc.getName(),
                doc.getSales() == null ? 0 : doc.getSales(),
                doc.getStock()
        );
    }
// hacer lo que hace el asMap pero en el stock, que quede encapsulado
    public ProductDocument toDocument(){
        ProductDocument doc = new ProductDocument();
        doc.setId(id);
        doc.setName(name.value());
        doc.setSales(sales.value());
        Map<String,Integer> newStock = new HashMap<>();
        stock.asMap().forEach((size, qty)-> newStock.put(size.name(), qty.value()));
        doc.setStock(newStock);
        return doc;
    }
// este tambien aplicar lo del asmap
    public int totalStock(){
        return stock.asMap().values().stream().mapToInt(Quantity::value).sum();
    }

    public String getName(){
        return name.value();
    }

    public int getSales(){
        return sales.value();
    }
}
