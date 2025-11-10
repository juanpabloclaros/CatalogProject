package com.acid.Project.Catalog.application.useCases;

import com.acid.Project.Catalog.domain.Shirt;
import com.acid.Project.Catalog.domain.ShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;

@Service
public class GetSortedListByStockAndSales {
    @Autowired
    private ShirtRepository shirtRepository;

    public List<Shirt> execute(double wStock, double wSales) {
        // ponerlo como VO el wStock y el wSales, y dentro se valida y se lanza throw en caso de que sea invalido(negativo o null)
        // en los VO meter un metodo isZero en lugar de comparar directamente en el if
        double ws = Math.max(0d, wStock);
        double wl = Math.max(0d, wSales);

        List<Shirt> all = shirtRepository.findAll();
        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.SECONDARY);

        boolean isAlphabetical = (ws == 0d && wl == 0d);
        Comparator<Shirt> sortByName = Comparator.comparing(Shirt::getName, collator);
        Comparator<Shirt> sortByWeight = Comparator
                .comparingDouble(
                        (Shirt s) -> (ws * s.totalStock()) + (wl * s.getSales())
                ).reversed()
                .thenComparing(Shirt::getName, collator);

        Comparator<Shirt> comparator = isAlphabetical ? sortByName : sortByWeight;

        return all.stream().sorted(comparator).toList();
    }
}
