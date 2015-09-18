package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Machine;

@Stateless
// @TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CarService {

	@PersistenceContext(name = "CarProject", type = PersistenceContextType.TRANSACTION)
	public EntityManager entity;

	/**
	 * Gets all machines from the database
	 * 
	 ** @return all the machines
	 */

	public List<Machine> getAll() {

		TypedQuery<Machine> query = entity.createNamedQuery(
				Machine.NQ_MACHINE_FIND_ALL, Machine.class);

		List<Machine> machines = query.getResultList();
		for (Machine machine : machines) {
			System.out.println(machine.getModel());
		}
		return machines;
	}

	/**
	 * Gets a machine from the database using the given Id
	 * 
	 * @param id
	 *            The machine Id
	 * @return the machine with the given Id
	 */
	public Machine get(int id) {
		Machine machine = entity.find(Machine.class, id);
		System.out.println(machine.getModel());
		return machine;
	}

	//@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void updateCarInDB(int carId, String carName) {
		entity.getTransaction().begin();
		Query query = entity.createQuery("UPDATE Machine SET Model = "
				+ carName + " WHERE MachineId = " + carId);
		query.executeUpdate();
		entity.getTransaction().begin();
//		entity.getTransaction().begin();
//		entity.merge(machine);
//		entity.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insertMachineInDB(Machine machine) {
		 entity.getTransaction().begin();
		 entity.persist(machine);
		 entity.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteCarFromDB(int carId) {
		entity.getTransaction().begin();
		entity.remove(get(carId));
		entity.flush();
	}

}
