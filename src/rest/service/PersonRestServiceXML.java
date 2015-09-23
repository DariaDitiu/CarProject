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
@Path("/personxml")
public class PersonRestServiceXML {

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
	@Path("/allxml")
	@Produces("application/xml")
	public List<PersonConfig> getAllPersonConfig() {
		return personService.getFromDBdataToXML();
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
	@Consumes("application/xml")
	public void deletePersonFromDBXML(@PathParam("personid") int personid) {
		personService.deletePersonFromDB(personid);
	}

	/**
	 * Method user to get from the web a person object and send it to the person
	 * service in order to add it in the database.
	 * 
	 * @param person
	 *            the object that will be obtain from the web, it will be in an
	 *            XML format
	 */
	@POST
	@Path("/addxml")
	@Consumes("application/xml")
	public void XMLaddNewPersonInDB(PersonConfig personConfig) {
		personService.insertPersonInDB(personConfig);
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
	@Path("/updatexml")
	@Consumes("application/xml")
	public void XMLupdatePersonInDB(PersonConfig personConfig) {
		personService.updatePersonInDB(personConfig);
	}

}
