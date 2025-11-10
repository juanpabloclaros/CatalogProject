package com.acid.Project.Catalog.infraestructure.repository;

import com.acid.Project.Catalog.infraestructure.documents.ShirtDocument;
import com.acid.Project.Catalog.domain.Shirt;
import com.acid.Project.Catalog.domain.ShirtRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ShirtRepositoryTestIntegration {

    @Autowired
    ShirtRepositoryMongoSpring shirtRepositoryMongoSpring;

    @BeforeEach
    void cleanData(){
        shirtRepositoryMongoSpring.deleteAll();
    }

    @Test
    void save_shirt(){
        ShirtRepository shirtRepository = new ShirtRepositoryMongo(shirtRepositoryMongoSpring);

        shirtRepository.save(Shirt.create(1L,"V-NECH BASIC SHIRT",100, Map.of("S",4,"M",9,"L",0)));
        Optional<ShirtDocument> shirtDocumentFound = shirtRepository.findById(1L);

        assertThat(shirtDocumentFound.isPresent()).isTrue();
    }

    @Test
    void retrieves_all_shirts_saved(){
        ShirtRepository shirtRepository = new ShirtRepositoryMongo(shirtRepositoryMongoSpring);

        shirtRepository.save(Shirt.create(1L,"V-NECH BASIC SHIRT",100, Map.of("S",4,"M",9,"L",0)));
        shirtRepository.save(Shirt.create(2L,"CONTRASTING FABRIC T-SHIRT",50, Map.of("S",35,"M",9,"L",9)));
        shirtRepository.save(Shirt.create(3L,"RAISED PRINT T-SHIRT",80, Map.of("S",20,"M",2,"L",20)));

        assertThat(shirtRepository.findAll().size()).isEqualTo(3);
    }
}
