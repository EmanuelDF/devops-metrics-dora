package com.devops.metrics;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class FrequenciaEntregaResourceTest {

    @Test
    public void testFrequenciaEntregaEndpoint() {
        given()
          .when().get("/Entrega/namespace/test-service")
          .then()
             .statusCode(200)
             .body(notNullValue());
    }

    @Test
    public void testFrequenciaRatingEndpoint() {
        given()
          .when().get("/Entrega/frequencia_rating/test-service")
          .then()
             .statusCode(200)
             .body(notNullValue());
    }

    @Test
    public void testFrequenciaRatingRegrasEndpoint() {
        given()
          .when().get("/Entrega/frequencia_rating_regras")
          .then()
             .statusCode(200)
             .body(notNullValue());
    }

}
