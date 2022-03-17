/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDTO {
    private long id;
    private String email;
    private String firstName;
    private String lastName;

    public PersonDTO(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static List<PersonDTO> getDtos(List<Person> ps){
        List<PersonDTO> pdtos = new ArrayList();
        ps.forEach(p->pdtos.add(new PersonDTO(p)));
        return pdtos;
    }


    public PersonDTO(Person p) {
        if(p.getId() != null)
            this.id = p.getId();
        this.email = p.getEmail();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
