package co.carlex.microservice.repository;

import co.carlex.microservice.usecases.model.Pet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 *
 * @author Carlex
 */
@Testcontainers
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PetRepositoryPostgresImplTest {
    
    PetRepositoryPostgresImpl repository;
    
    @Autowired
    DataSource dataSource;
    
    @Container
    static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("test")
            .withUsername("sa")
            .withPassword("sa");
    
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }
    
    @BeforeEach
    void setup() throws SQLException{
        Connection conn = dataSource.getConnection();
        conn.createStatement().execute("CREATE TABLE IF NOT EXISTS pet(name VARCHAR(50), adopted BOOLEAN, PRIMARY KEY(name))");
    }
    
    @Test
    public void testSavePet_newPet() throws SQLException {
        repository = new PetRepositoryPostgresImpl();
        repository.dataSource = dataSource;
        Pet actual = repository.savePet(new Pet("Yup", false));
        assertEquals("Yup", actual.getName());
        assertFalse(actual.isAdopted());
        Connection conn = dataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT name, adopted FROM pet WHERE name = 'Yup'");
        assertTrue(rs.next(), "Row no found in db");
        assertEquals("Yup", rs.getString("name"));
        assertFalse(rs.getBoolean("adopted"));
    }
    
}
