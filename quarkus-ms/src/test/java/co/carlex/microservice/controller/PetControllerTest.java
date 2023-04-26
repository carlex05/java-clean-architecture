package co.carlex.microservice.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PetControllerTest {

    @Test
    public void registerUnadoptedPet() {
        given()
          .when().put("/pets/Luky")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));//TODO: finish unit test
    }

}