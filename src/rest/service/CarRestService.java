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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import com.fortech.jaxb.MachineConfig;


import model.Machine;
import service.CarService;

/**
 * Class that is used as a REST service class. In here the communication with
 * the web is made.
 * 
 * @author dariad
 *
 */
@Stateless
@Path("/car")
public class CarRestService {

	/**
	 * EJB used to communicate with the service of machine.
	 */
	@EJB
	private CarService service;

	/**
	 * Method that returns to web all the machines that are in DB
	 * 
	 * @return a list of machines
	 */
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<Machine> getAllCars() {
		return service.getAll();
	}

	/**
	 * Method that returns to web all the machines that are in DB
	 * 
	 * @return a list of machines
	 */
	@GET
	@Path("/allxml")
	@Produces("application/xml")
	public List<MachineConfig> getAllMachineConfigs() {
		return service.getFromDBdataToXML();
	}

	/**
	 * Gets a machine from service the machine using the given Id
	 * 
	 * @param id
	 *            The machine Id that will be returned from DB
	 * 
	 * @return the machine with the given Id sent as a parameter
	 */
	@GET
	@Path("{machineid}")
	@Produces("application/json")
	public Machine getCar(@PathParam("machineid") int id) {

		Machine machine = service.get(id);
		// System.out.println(machine);
		return machine;
	}

	/**
	 * Method that takes a machine from web and send it to the service to be put
	 * to DB
	 * 
	 * @param machine
	 *            The object that is obtained from web
	 */
	@POST
	@Path("/add")
	@Consumes("application/json")
	public void addCar(Machine machine) {

		service.insertMachineInDB(machine);

	}

	/**
	 * Method that takes a machine from web and send it to the service to be put
	 * to DB
	 * 
	 * @param machine
	 *            The object that is obtained from web (xml format)
	 */
	@POST
	@Path("/addxml")
	@Consumes("application/xml")
	public void addCarXml(MachineConfig carConfig) {

		service.insertMachineInDB(carConfig);
	}

	/**
	 * Method used to update an existing machine in DB with the new information
	 * received from web.
	 * 
	 * @param machine
	 *            The machine taken from web and which whom the machine from DB
	 *            with the same id is updated
	 * 
	 */
	@PUT
	@Path("/update")
	@Consumes("application/json")
	public void updateCar(Machine machine) {

		service.updateMachineInDB(machine);

	}

	/**
	 * Method used to update an existing machine in DB with the new information
	 * received from web.
	 * 
	 * @param machine
	 *            The machine taken from web and which whom the machine from DB
	 *            with the same id is updated
	 * 
	 */
	@PUT
	@Path("/updatexml")
	@Consumes("application/xml")
	public void updateCarXml(MachineConfig carConfig) {

		service.updateMachineInDB(carConfig);

	}

	/**
	 * Method used to delete a machine from the database.
	 * 
	 * @param id
	 *            The id of the machine that will be deleted from db
	 */
	@DELETE
	@Path("/delete/{machineid}")
	@Consumes("application/json")
	public void deleteCar(@PathParam("machineid") int id) {
		service.deleteCarFromDB(id);
	}

	/**
	 * Method used to delete a machine from the database.
	 * 
	 * @param id
	 *            The id of the machine that will be deleted from db
	 */
	@DELETE
	@Path("/deletexml/{machineid}")
	@Consumes("application/xml")
	public void deleteCarXML(@PathParam("machineid") int id) {
		service.deleteCarFromDB(id);
	}

	@GET
	@Path("/marshal")
	@Produces("application/xml")
	public String marshallString() throws JAXBException {

		MachineConfig machineConfig = new MachineConfig();
		machineConfig.setMachineid(10);
		;
		machineConfig.setModel("Opel");

		StringWriter sw = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(MachineConfig.class);
		Marshaller jMarshaller = jaxbContext.createMarshaller();
		jMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jMarshaller.marshal(machineConfig, sw);
		return sw.toString();
	}

}
