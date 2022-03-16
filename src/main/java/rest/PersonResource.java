package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import entities.Person;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("xxx")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("person/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<PersonDTO> getAllPersons(){
        List<PersonDTO> persons = FACADE.getAllPersons();
        return persons;
    }

    @Path("person/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public PersonDTO getById(@PathParam("id") long id){
        PersonDTO personDTO = FACADE.getById(id);
        return personDTO;
    }

    @Path("person/count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonCount(){
        long count = FACADE.getPersonCount();
        return "{\"count\":"+count+"}";
    }

    @Path("person/delete/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public boolean deletePerson(@PathParam("id") long id){
        boolean personDTO = FACADE.deletePerson(id);
        return personDTO;
    }

    @Path("person/add")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public PersonDTO create(PersonDTO p){
        PersonDTO personDTO = FACADE.create(p);
        return personDTO;
    }

//    @Path("person/edit/{id}")
//    @PUT
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public PersonDTO editPerson(@PathParam("id") long id, String email, String firstName, String lastName){
//        PersonDTO personDTO = FACADE.editPerson(id, email, firstName, lastName);
//        return personDTO;
//    }


}
