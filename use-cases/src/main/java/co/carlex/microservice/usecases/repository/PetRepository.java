package co.carlex.microservice.usecases.repository;

import co.carlex.microservice.usecases.model.Pet;

/**
 *
 * @author Carlex
 */
public interface PetRepository {
    
    Pet savePet(Pet pet);
    
}
