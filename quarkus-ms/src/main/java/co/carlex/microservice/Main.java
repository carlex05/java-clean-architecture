package co.carlex.microservice;

import co.carlex.microservice.usecases.repository.PetRepository;
import co.carlex.microservice.usecases.service.PetService;
import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author Carlex
 */
@ApplicationScoped
public class Main {
    
    PetRepository repository;
    
    @ApplicationScoped
    PetService getPetService(){
        return new PetService(repository);
    }
    
}
