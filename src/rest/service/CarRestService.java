package rest.service;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


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
		
		//return Response.status(200).entity(machines).build();
	}
	
	@GET
	@Path("{id}")
	public void getCar(@PathParam("id") int id) {

		Machine machine = service.get(id);
		System.out.println(machine);
		//return Response.ok(machine).build();
	}
	
	
	
	

}