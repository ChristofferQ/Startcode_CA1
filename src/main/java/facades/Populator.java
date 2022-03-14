/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import entities.Person;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();

        PersonFacade pf = PersonFacade.getPersonFacade(emf);
        pf.create(new PersonDTO(new Person("email1","firstname1","lastname1")));
        pf.create(new PersonDTO(new Person("email2","firstname2","lastname2")));
        pf.create(new PersonDTO(new Person("email3","firstname3","lastname3")));

    }
    
    public static void main(String[] args) {
        populate();
    }
}
