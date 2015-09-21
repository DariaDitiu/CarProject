package rest.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import service.PersonService;
import model.Person;

@Stateless
@Path("/person")
public class PersonRestService {

	@EJB
	private PersonService personService;

	@GET
	@Path("/all")
	@Produces("application/xml")
	public List<PersonConfig> getAllPersons() {
		return personService.getAll();
	}

	@GET
	@Path("/select")
	public Person getPerson(@QueryParam("id") int id) {
		return personService.get(id);
	}

	@DELETE
	@Path("/delete/{personid}")
	public void deletePersonFromDB(@PathParam("personid") int personid) {
		personService.deletePersonFromDB(personid);
	}

	@POST
	@Path("/add")
	@Consumes("application/json")
	public void addNewPersonInDB(Person person) {
		personService.insertPersonInDB(person);
	}

	@PUT
	@Path("/update")
	@Consumes("application/json")
	public void updatePersonInDB(Person person) {
		personService.updatePersonInDB(person);
	}
}
