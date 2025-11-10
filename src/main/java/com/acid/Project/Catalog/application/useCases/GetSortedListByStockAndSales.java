package com.acid.Project.Catalog.application.useCases;

import com.acid.Project.Catalog.domain.Product;
import com.acid.Project.Catalog.domain.ProductRepository;
import com.acid.Project.Catalog.domain.WeightSales;
import com.acid.Project.Catalog.domain.WeightStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;

@Service
public class GetSortedListByStockAndSales {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> execute(double wStock, double wSales) {
        WeightStock weightStock = WeightStock.from(wStock);
        WeightSales weightSales = WeightSales.from(wSales);

        List<Product> allProducts = productRepository.findAll();

        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.SECONDARY);

        Comparator<Product> comparator = getProductComparator(weightSales, weightStock, collator);

        return allProducts.stream().sorted(comparator).toList();
    }

    private static Comparator<Product> getProductComparator(WeightSales weightSales, WeightStock weightStock, Collator collator) {
        boolean isAlphabetical = (weightSales.isZero() && weightStock.isZero());

        Comparator<Product> sortByName = Comparator.comparing(Product::getName, collator);
        Comparator<Product> sortByWeight = Comparator
                .comparingDouble(
                        (Product s) -> (weightStock.value() * s.totalStock()) + (weightSales.value() * s.getSales())
                ).reversed()
                .thenComparing(Product::getName, collator);

        return isAlphabetical ? sortByName : sortByWeight;
    }
}
