package gr.ichrist.service;

import gr.ichrist.model.Person;
import gr.ichrist.repository.PersonRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Uni<Response> savePerson(Person person) {
        return personRepository.persist(person)
                .onItem()
                .transform(prs -> Response.ok().build());
    }

    public Uni<List<Person>> fetchPersonList() {
        return personRepository.findAll().list();
    }

    public Uni<Person> fetchPersonById(String objectId) {
        return personRepository.findById(new ObjectId(objectId));
    }
}
