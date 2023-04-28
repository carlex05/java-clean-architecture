/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.carlex.microservice.repository;

import co.carlex.microservice.usecases.model.Pet;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.maven.model.Repository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

/**
 *
 * @author Carlex
 */
@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PetRepositoryPostgresImplTest {
    
    @Inject
    PetRepositoryPostgresImpl repository;
    
    @Inject
    DataSource dataSource;

    @BeforeAll
    public void setup() throws SQLException{
        Connection conn = dataSource.getConnection();
        conn.createStatement().execute("CREATE TABLE IF NOT EXISTS pet(name VARCHAR(50), adopted BOOLEAN, PRIMARY KEY(name))");
        conn.createStatement().execute("INSERT INTO pet VALUES('Kyo', false), ('Jub', false)");
    }
    
    @Test
    public void testSavePet_newPet() throws SQLException {
        Pet actual = repository.savePet(new Pet("Yup", false));
        assertEquals("Yup", actual.getName());
        assertFalse(actual.isAdopted());
        Connection conn = dataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT name, adopted FROM pet WHERE name = 'Yup'");
        assertTrue(rs.next(), "Row no found in db");
        assertEquals("Yup", rs.getString("name"));
        assertFalse(rs.getBoolean("adopted"));
    }
    
    @Test
    public void testSavePet_updatePet() throws SQLException {
        Pet actual = repository.savePet(new Pet("Jub", true));
        assertEquals("Jub", actual.getName());
        assertTrue(actual.isAdopted());
        Connection conn = dataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT name, adopted FROM pet WHERE name = 'Jub'");
        assertTrue(rs.next(), "Row no found in db");
        assertEquals("Jub", rs.getString("name"));
        assertTrue(rs.getBoolean("adopted"));
    }
    
    @Test
    public void testSavePet_sqlException() throws SQLException {
        assertThrows(IllegalStateException.class, () -> repository.savePet(new Pet("Jubfgasdfgsdfgsdfgkjadflgjsdfgksdflkñgsdñfklgsdfgjdfgfbghjasdfgsdfgmns,dfngskdfngjkafdfgjabdsfhjgsbdfhjgbsdfkhjgbkafgfghsdfjghsdfklgnsdfkjgbsdlfkghahfgasdgf", true)));
    }
    
}
