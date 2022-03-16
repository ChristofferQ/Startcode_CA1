package facades;

import dtos.PersonDTO;
import entities.Person;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {}

    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PersonDTO create(PersonDTO p){
        Person pe = new Person(p.getEmail(), p.getFirstName(), p.getLastName());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pe);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(pe);
    }

    public PersonDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);
//        if (p == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new PersonDTO(p);
    }

    public long getPersonCount(){
        EntityManager em = getEntityManager();
        try {
            long personCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return personCount;
        } finally {
            em.close();
        }
    }
    
    public List<PersonDTO> getAllPersons(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> ps = query.getResultList();
        return PersonDTO.getDtos(ps);
    }

    public boolean deletePerson(long id){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Person p WHERE p.id = :id").setParameter("id",id);
            query.executeUpdate();
            em.getTransaction().commit();
            return true;
        } finally {
            em.close();
        }
    }

//    public PersonDTO editPerson(long id, String email, String firstName, String lastName) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            Person p = em.find(Person.class, id);
////                if (p == null)
////                    throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
////            }
//            em.getTransaction().begin();
//            Query query = em.createQuery("UPDATE Person p SET p.email = 'email', p.firstName = 'firstName', p.lastName = 'lastName' WHERE p.id = :id");
//            query.executeUpdate();
//            em.getTransaction().commit();
//            return new PersonDTO(p);
//        } finally {
//            em.close();
//        }
//
//    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = getPersonFacade(emf);
        pf.getAllPersons().forEach(dto->System.out.println(dto));
    }

}
