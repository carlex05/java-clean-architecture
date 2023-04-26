package co.carlex.microservice.usecases.service;

import co.carlex.microservice.usecases.repository.PetRepository;
import co.carlex.microservice.usecases.model.Pet;
import java.util.Objects;

/**
 * 
 * @author Carlex
 */
public class PetService {
    
    PetRepository repository;
    
    public Pet registerUnadoptedPet(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Pet's name is required");
        }
        Pet pet = new Pet(name, false);
        return repository.savePet(pet);
    }
    
}
