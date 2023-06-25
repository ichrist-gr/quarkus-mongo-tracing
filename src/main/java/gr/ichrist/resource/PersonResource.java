package gr.ichrist.resource;

import gr.ichrist.model.Person;
import gr.ichrist.service.PersonService;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/mongo")
public class PersonResource {
    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GET
    @Path("/retrieve/persons")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Person>> retrievePersons() {
        return personService.fetchPersonList();
    }

    @POST
    @Path("/persist/person")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> persistPerson(Person person) {
        return personService.savePerson(person);
    }

    @GET
    @Path("/retrieve/person/{objectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Person> retrievePersonByUsername(@RestPath("objectId") String objectId) {
        return personService.fetchPersonById(objectId);
    }
}
