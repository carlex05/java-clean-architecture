package co.carlex.microservice.controller;

import co.carlex.microservice.usecases.model.Pet;
import co.carlex.microservice.usecases.service.PetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PetControllerTest {

    @Inject
    ObjectMapper mapper;
    
    @Test
    public void registerUnadoptedPet() throws JsonProcessingException {
        String jsonExpected = mapper.writeValueAsString(new Pet("Luky", false));
        given()
          .when().put("/pets/Luky")
          .then()
             .statusCode(200)
             .body(is(jsonExpected));
    }
    
    @Mock
    @ApplicationScoped
    PetService mockService(){
        return new PetService(){ 
            @Override
            public Pet registerUnadoptedPet(String name) {
                return new Pet(name, false);
            }
            
        };
    }

}