package e2e;

import com.acid.Project.Catalog.domain.Product;
import com.acid.Project.Catalog.domain.ProductRepository;
import com.acid.Project.Catalog.infraestructure.repository.ProductRepositoryMongo;
import com.acid.Project.Catalog.infraestructure.repository.ProductRepositoryMongoSpring;
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
    ProductRepositoryMongoSpring productRepositoryMongoSpring;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";

        ProductRepository productRepository = new ProductRepositoryMongo(productRepositoryMongoSpring);
        productRepository.deleteAll();

        productRepository.save(Product.create(1L,"V-NECH BASIC SHIRT",100, Map.of("S",4,"M",9,"L",0)));
        productRepository.save(Product.create(2L,"CONTRASTING FABRIC T-SHIRT",50, Map.of("S",35,"M",9,"L",9)));
        productRepository.save(Product.create(3L,"RAISED PRINT T-SHIRT",80, Map.of("S",20,"M",2,"L",20)));
    }

    @Test
    void order_by_sales_when_wSales_is_greater_than_wStock(){
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

    @Test
    void order_by_stock_when_wStock_is_greater_than_wSales(){
        given()
                .queryParam("wStock",1.0)
                .queryParam("wSales",0.2)
                .when()
                .get("/api/products/weighted")
                .then()
                .statusCode(200)
                .body("$", hasSize(3))
                .body("[0].id",equalTo(2))
                .body("[1].id",equalTo(3))
                .body("[2].id",equalTo(1));
    }

    @Test
    void order_by_name_when_wStock_and_wSales_are_zero(){
        given()
                .queryParam("wStock",0.0)
                .queryParam("wSales",0.0)
                .when()
                .get("/api/products/weighted")
                .then()
                .statusCode(200)
                .body("$", hasSize(3))
                .body("[0].id",equalTo(2))
                .body("[1].id",equalTo(3))
                .body("[2].id",equalTo(1));
    }
}
