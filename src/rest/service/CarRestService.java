package rest.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.Machine;
import service.CarService;

@Stateless
@Path("/car")
public class CarRestService {

	@EJB
	private CarService service;

	@GET
	public void getAllCars() {

		//List<Machine> machines = service.getAll();
		// return Response.status(200).entity(machines).build();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getCar(@PathParam("id") int id) {

		Machine machine = service.get(id);
		System.out.println(machine);
		return Response.ok(machine).build();
	}

	@POST
	@Path("/add")
	@Consumes("application/json")
	public void addCar(Machine machine) {

		service.insertMachineInDB(machine);

	}

	@PUT
	@Path("/update")
	@Consumes("application/json")
	public void updateCar(Machine machine) {

		service.updateMachineInDB(machine);

	}

	// @DELETE
	// @Path("/{id}")
	// public String deleteCar(@PathParam("id") int idCar){
	//
	// }

}