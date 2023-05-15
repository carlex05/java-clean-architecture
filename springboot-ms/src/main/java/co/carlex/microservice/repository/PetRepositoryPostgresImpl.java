package co.carlex.microservice.repository;

import co.carlex.microservice.usecases.model.Pet;
import co.carlex.microservice.usecases.repository.PetRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlex
 */
@Repository
public class PetRepositoryPostgresImpl implements PetRepository {
    
    @Autowired
    DataSource dataSource;

    @Override
    public Pet savePet(Pet pet) {
        String sql = "INSERT INTO pet(name, adopted) VALUES(?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pet.getName());
            statement.setBoolean(2, pet.isAdopted());
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                return pet;
            } else {
                throw new SQLException("Creating pet failed, no rows affected.");
            }
        }catch(SQLException ex){
            throw new IllegalStateException(ex);
        }
    }
}
