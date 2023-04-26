package co.carlex.microservice.controller;

import co.carlex.microservice.usecases.model.Pet;
import co.carlex.microservice.usecases.service.PetService;
import jakarta.inject.Inject;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/pets")
public class PetController {

    @Inject
    PetService service;
    
    @PUT
    @Path("/{pet_name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pet registerUnadoptedPet(@PathParam("pet_name") String petName) {
        
        return null;
    }
}
