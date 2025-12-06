package com.devops.metrics;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TempoEntregaResourceTest {

    @Test
    public void testTempoEntregaEndpoint() {
        given()
          .when().get("/tempoEntrega/namespace/test-service")
          .then()
             .statusCode(200);
    }

}
