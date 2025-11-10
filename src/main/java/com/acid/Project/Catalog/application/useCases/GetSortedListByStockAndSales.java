package com.acid.Project.Catalog.application.useCases;

import com.acid.Project.Catalog.domain.Product;
import com.acid.Project.Catalog.domain.ProductRepository;
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
        // ponerlo como VO el wStock y el wSales, y dentro se valida y se lanza throw en caso de que sea invalido(negativo o null)
        // en los VO meter un metodo isZero en lugar de comparar directamente en el if
        double ws = Math.max(0d, wStock);
        double wl = Math.max(0d, wSales);

        List<Product> all = productRepository.findAll();
        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.SECONDARY);

        boolean isAlphabetical = (ws == 0d && wl == 0d);
        Comparator<Product> sortByName = Comparator.comparing(Product::getName, collator);
        Comparator<Product> sortByWeight = Comparator
                .comparingDouble(
                        (Product s) -> (ws * s.totalStock()) + (wl * s.getSales())
                ).reversed()
                .thenComparing(Product::getName, collator);

        Comparator<Product> comparator = isAlphabetical ? sortByName : sortByWeight;

        return all.stream().sorted(comparator).toList();
    }
}
