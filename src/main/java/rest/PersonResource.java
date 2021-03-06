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
        //Change personDTO to something like status
        boolean personDTO = FACADE.deletePerson(id);
        return personDTO;
    }


//Change to response instead of String.
    @Path("person/add")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String create(String person){
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        PersonDTO pNew = FACADE.create(p);
        return GSON.toJson(pNew);
    }

    @Path("person/edit/{id}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String editPerson(@PathParam("id") long id, String person){
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        p.setId(id);
        PersonDTO pEdited = FACADE.editPerson(p);
        return GSON.toJson(pEdited);
    }


}
