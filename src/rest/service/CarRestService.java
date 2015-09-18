package rest.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

		List<Machine> machines = service.getAll();

		// return Response.status(200).entity(machines).build();
	}

	@GET
	@Path("{id}")
	public void getCar(@PathParam("id") int id) {

		Machine machine = service.get(id);
		System.out.println(machine);
		// return Response.ok(machine).build();
	}

	@POST
	@Path("/update")
	public Response updateCar(@FormParam("CarId") int id,
			@FormParam("model") String model) {

		String response = "<html><body>" + "Id car is: " + id
				+ "and the model:" + model + "<body></html>";
		return Response.status(200).entity(response).build();

	}

	@PUT
	@Path("/add")
	public Response addCar(@FormParam("CarId") int id,
			@FormParam("model") String model) {

		return Response.status(200)
				.entity("Id car is: " + id + "and the model:" + model).build();

	}

	// @DELETE
	// @Path("/{id}")
	// public String deleteCar(@PathParam("id") int idCar){
	//
	// }

}