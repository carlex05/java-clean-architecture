package co.carlex.microservice;

import co.carlex.microservice.usecases.service.PetService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carlex
 */
@QuarkusTest
public class MainTest {

    @Inject
    PetService service;
    
    @Test
    public void testServiceProvided() {
        assertNotNull(service);
        assertNotNull(service.getRepository());
    }
    
}
