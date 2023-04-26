package co.carlex.microservice.usecases.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Carlex
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    
    private String name;
    private boolean adopted;
    
}
