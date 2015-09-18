package rest.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

	@POST
	@Path("/add")
	public Response addUser(@FormParam("name") String name, @FormParam("age") int age) {	
		return Response.status(200)
					   .entity("addUser is called, name : " + name + ", age : " + age).build();
	}

	@GET
	@Path("/{id}")
	public void getCar(@PathParam("id") int id) {
		Machine m = service.get(2);
		//m.setModel("bbb");
		//service.updateCarInDB(2,"Nema");
		// Machine machine = service.get(2);
		System.out.println(m);
	}
	
	@DELETE
	@Path("/delete/{carId}")
	public void deleteCarRestService(@PathParam("carId") int carId){
		System.out.println(carId);
		service.deleteCarFromDB(carId);
		System.out.println("Car deleted");
	}

}