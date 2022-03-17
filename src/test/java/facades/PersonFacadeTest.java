package facades;

import dtos.PersonDTO;
import entities.Person;
import org.glassfish.json.JsonUtil;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;

    private static Person p1, p2, p3;

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = PersonFacade.getPersonFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(p1 = new Person("emailTest1", "firstNameTest1","lastNameTest1"));
            em.persist(p2 = new Person("emailTest2", "firstNameTest2","lastNameTest2"));
            em.persist(p3 = new Person("emailTest3", "firstNameTest3","lastNameTest3"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method
    @Test
    public void testAFacadeMethod() throws Exception {
        assertEquals(2, facade.getPersonCount(), "Expects two rows in the database");
    }

    @Test
    public void testGetPersonCount(){
        System.out.println("getPersonCount");
        EntityManagerFactory emf = null;
        PersonFacade instance = PersonFacade.getPersonFacade(emf);
        long expected = 3L;
        long actual = instance.getPersonCount();
        assertEquals(expected, actual);
        System.out.println("Expected = " + expected + "\nActual = " + actual);
    }

    @Test
    public void testCreate(){
        System.out.println("create");
        String email = "emailTest3";
        String firstName = "firstNameTest3";
        String lastName = "lastNameTest3";
        EntityManagerFactory emf = null;
        PersonFacade instance = PersonFacade.getPersonFacade(emf);
        PersonDTO expected = new PersonDTO(email, firstName, lastName);
        PersonDTO actual = instance.create(new PersonDTO(email, firstName, lastName));
        expected.setId(actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPerson(){
        System.out.println("getById");
        long id = p3.getId();
        EntityManagerFactory emf = null;
        PersonFacade instance = PersonFacade.getPersonFacade(emf);
        PersonDTO expected = new PersonDTO(p3);
        PersonDTO actual = instance.getById(id);
        assertEquals(expected, actual);
    }

}
