package co.carlex.microservice.repository;

import co.carlex.microservice.usecases.model.Pet;
import co.carlex.microservice.usecases.repository.PetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Carlex
 */
@ApplicationScoped
public class PetRepositoryPostgresImpl implements PetRepository {
    
    @Inject
    DataSource dataSource;
    
    @Override
    @Transactional
    public Pet savePet(Pet pet) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmnt = conn.prepareStatement(
                    """
                    INSERT INTO pet(name, adopted) VALUES(?,?) 
                     ON CONFLICT (name) DO UPDATE SET adopted = EXCLUDED.adopted
                    """
            );
            pstmnt.setString(1, pet.getName());
            pstmnt.setBoolean(2, pet.isAdopted());
            pstmnt.executeUpdate();
            return pet;
        } catch (SQLException ex) {
            Logger.getLogger(PetRepositoryPostgresImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException(ex);
        }
    }
    
}
