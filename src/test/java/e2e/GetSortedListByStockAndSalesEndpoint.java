package e2e;

import com.acid.Project.Catalog.domain.Shirt;
import com.acid.Project.Catalog.domain.ShirtRepository;
import com.acid.Project.Catalog.infraestructure.repository.ShirtRepositoryMongo;
import com.acid.Project.Catalog.infraestructure.repository.ShirtRepositoryMongoSpring;
import com.acid.Project.ProjectApplication;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(classes = ProjectApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetSortedListByStockAndSalesEndpoint {
    @LocalServerPort
    private int port;

    @Autowired
    ShirtRepositoryMongoSpring shirtRepositoryMongoSpring;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";

        ShirtRepository shirtRepository = new ShirtRepositoryMongo(shirtRepositoryMongoSpring);
        shirtRepository.deleteAll();

        shirtRepository.save(Shirt.create(1L,"V-NECH BASIC SHIRT",100, Map.of("S",4,"M",9,"L",0)));
        shirtRepository.save(Shirt.create(2L,"CONTRASTING FABRIC T-SHIRT",50, Map.of("S",35,"M",9,"L",9)));
        shirtRepository.save(Shirt.create(3L,"RAISED PRINT T-SHIRT",80, Map.of("S",20,"M",2,"L",20)));
    }

    // añadir los mismos casos que el caso de uso
    @Test
    void order_by_weight(){
        given()
                .queryParam("wStock",0.2)
                .queryParam("wSales",1.0)
                .when()
                .get("/api/products/weighted")
                .then()
                .statusCode(200)
                .body("$", hasSize(3))
                .body("[0].id",equalTo(1))
                .body("[1].id",equalTo(3))
                .body("[2].id",equalTo(2));
    }
}
