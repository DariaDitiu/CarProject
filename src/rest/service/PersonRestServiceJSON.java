package rest.service;

import java.io.StringWriter;
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
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fortech.jaxb.PersonConfig;

import service.PersonService;
import model.Person;

/**
 * Class that is used as a REST service class. In here the communication with
 * the web is made.
 * 
 * @author lucian.tuduce
 *
 */
@Stateless
@Path("/personjson")
public class PersonRestServiceJSON {

	/**
	 * EJB used to communicate with the database service of the person.
	 */
	@EJB
	private PersonService personService;

	/**
	 * Method user to return in the web all the users that are present in the
	 * database.
	 * 
	 * @return a list of users
	 */
	@GET
	@Path("/alljson")
	@Produces("application/json")
	public List<Person> getAllPerson() {
		return personService.getAll();
	}

	/**
	 * Method user to get from the person service the person that has the id
	 * offered as parameter.
	 * 
	 * @param id
	 *            the id of the person that will be returned from the database
	 * @return person object with the id offered as parameter
	 */
	@GET
	@Path("/select")
	public Person getPerson(@QueryParam("id") int id) {
		return personService.get(id);
	}

	/**
	 * Method used to delete a person from the database.
	 * 
	 * @param personid
	 *            the id of the person that will be deleted from database
	 */
	@DELETE
	@Path("/delete/{personid}")
	@Consumes("application/json")
	public void deletePersonFromDBJSON(@PathParam("personid") int personid) {
		personService.deletePersonFromDB(personid);
	}

	/**
	 * Method user to get from the web a person object and send it to the person
	 * service in order to add it in the database.
	 * 
	 * @param person
	 *            the object that will be obtain from the web, it will be in an
	 *            JSON format
	 */
	@POST
	@Path("/addjson")
	@Consumes("application/json")
	public void JSONaddNewPersonInDB(Person person) {
		personService.insertPersonInDB(person);
	}

	/**
	 * Method used to update a existing person in the database. The new
	 * attributes of the person object will be obtained from the web.
	 * 
	 * @param person
	 *            the person object that will be put over the existing one in
	 *            order to update it
	 */
	@PUT
	@Path("/updatejson")
	@Consumes("application/json")
	public void JSONupdatePersonInDB(Person person) {
		personService.updatePersonInDB(person);
	}

	@GET
	@Path("/marshal")
	public Response marshallString() throws JAXBException {
		PersonConfig personConfig = new PersonConfig();
		personConfig.setPersonid(10);
		personConfig.setFirstname("Lucian");
		personConfig.setLastname("Tuduce");

		StringWriter sw = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(PersonConfig.class);
		Marshaller jMarshaller = jaxbContext.createMarshaller();
		jMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jMarshaller.marshal(personConfig, sw);
		return Response.status(200).entity(sw.toString()).build();

	}

}
