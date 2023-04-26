package co.carlex.microservice.usecases.service;

import co.carlex.microservice.usecases.repository.PetRepository;
import co.carlex.microservice.usecases.model.Pet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.mockito.Mockito.*;

/**
 *
 * @author Carlex
 */
public class PetServiceTest {

    
    
    @Test
    public void testRegisterUnadoptedPet_success() {
        PetRepository repository = mock(PetRepository.class);
        when(repository.savePet(any(Pet.class))).thenReturn(new Pet("Luky", false));
        PetService service = new PetService();
        service.repository = repository;
        Pet pet = service.registerUnadoptedPet("Luky");
        assertEquals("Luky", pet.getName());
        assertFalse(pet.isAdopted());
        verify(repository, only()).savePet(any());
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    public void testRegisterUnadoptedPet_nullAndEmptyParam(String petName) {
        PetService service = new PetService();
        assertThrows(IllegalArgumentException.class, () -> service.registerUnadoptedPet(petName));
    }
    
}
