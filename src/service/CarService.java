package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import model.Machine;
import model.Person;

import com.fortech.jaxb.MachineConfig;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CarService {

	@PersistenceContext(name = "CarProject", type = PersistenceContextType.TRANSACTION)
	public EntityManager entity;

	/**
	 * Gets all machines from the database
	 * 
	 ** @return list with all the machines
	 */
	public List<Machine> getAll() {

		TypedQuery<Machine> query = entity.createNamedQuery(
				Machine.NQ_MACHINE_FIND_ALL, Machine.class);

		List<Machine> machinesResult = new ArrayList<Machine>();
		List<Machine> machines = new ArrayList<Machine>(query.getResultList());
		for (Machine machine : machines) {
			Machine machineResult = new Machine();
			machineResult.setMachineid(machine.getMachineid());
			machineResult.setModel(machine.getModel());

			if (machine.getPerson() != null) {
				Person personResult = new Person();
				personResult.setFirstname(machine.getPerson().getFirstname());
				personResult.setLastname(machine.getPerson().getLastname());
				personResult.setPersonid(machine.getPerson().getPersonid());
				machineResult.setPerson(personResult);
			}
			machinesResult.add(machineResult);
			//System.out.println(machine.getModel());
		}
		return machinesResult;
	}

	/**
	 * Gets a machine from the database using the given Id
	 * 
	 * @param id
	 *            The machine Id
	 * 
	 * @return the machine with the given Id
	 */
	public Machine get(int id) {

		Machine machine = entity.find(Machine.class, id);
		return machine;
	}

	/**
	 * Method used to add a machine in DB
	 * 
	 * @param machine
	 *            The machine that is inserted into DB
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insertMachineInDB(Machine machine) {
		entity.getTransaction().begin();
		entity.persist(machine);
		entity.getTransaction().commit();
	}

	/**
	 * Method used to add a machine in DB
	 * 
	 * @param machine
	 *            The machine that is inserted into DB
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insertMachineInDB(MachineConfig machine) {

		Machine p = new Machine();
		p.setMachineid(machine.getMachineid());
		p.setModel(machine.getModel());

		entity.getTransaction().begin();
		entity.persist(p);
		entity.getTransaction().commit();
	}

	/**
	 * Method used to update a machine in DB
	 * 
	 * @param machine
	 *            The machine with an id that is already in DB is updated
	 */
	public void updateMachineInDB(Machine machine) {

		Machine car = entity.find(Machine.class, machine.getMachineid());
		entity.getTransaction().begin();
		car.setModel(machine.getModel());
		entity.getTransaction().commit();
	}

	/**
	 * Method used to update a machine in DB
	 * 
	 * @param machine
	 *            The machine with an id that is already in DB is updated
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateMachineInDB(MachineConfig machine) {
		Machine p = new Machine();
		p.setMachineid(machine.getMachineid());
		p.setModel(machine.getModel());

		entity.getTransaction().begin();
		entity.merge(p);
		entity.getTransaction().commit();
	}

	/**
	 * Method that delete's a machine from the database based on the machine id.
	 * 
	 * @param machineId
	 *            The machine id that will be deleted
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteCarFromDB(int machineid) {
		entity.getTransaction().begin();
		entity.remove(get(machineid));
		entity.getTransaction().commit();
	}

	/**
	 * Method user to get the data from db and add it in the JAXB class.
	 */
	public List<MachineConfig> getFromDBdataToXML() {
		MachineConfig machineConfig = null;
		TypedQuery<Machine> query = entity.createNamedQuery(
				Machine.NQ_MACHINE_FIND_ALL, Machine.class);
		List<Machine> machines = query.getResultList();
		List<MachineConfig> machineConfigs = new ArrayList<MachineConfig>();
		for (Machine machine : machines) {
			machineConfig = new MachineConfig();
			machineConfig.setMachineid(machine.getMachineid());
			;
			machineConfig.setModel(machine.getModel());
			machineConfigs.add(machineConfig);
		}

		for (MachineConfig p : machineConfigs) {
			System.out.println(p.getMachineid() + " " + p.getModel());
		}
		return machineConfigs;
	}

}
