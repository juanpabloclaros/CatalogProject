package com.acid.Project.Catalog;

import com.acid.Project.Catalog.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DataSeeder implements ApplicationRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) {
        productRepository.save(Product.create(1L,"V-NECH BASIC SHIRT",100, Map.of("S",4, "M",9,"L",0)));
        productRepository.save(Product.create(2L,"CONTRASTING FABRIC T-SHIRT",50, Map.of("S",35,"M",9,"L",9)));
        productRepository.save(Product.create(3L,"RAISED PRINT T-SHIRT",80, Map.of("S",20,"M",2,"L",20)));
        productRepository.save(Product.create(4L,"PLEATED T-SHIRT",3, Map.of("S",25, "M",30,"L",10)));
        productRepository.save(Product.create(5L,"CONTRASTING LACE T-SHIRT",650, Map.of("S",0,"M",1,"L",0)));
        productRepository.save(Product.create(6L,"SLOGAN T-SHIRT",20, Map.of("S",9,"M",2,"L",5)));
    }
}
