package service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.apache.commons.beanutils.BeanUtils;

import com.fortech.jaxb.PersonConfig;

import model.Person;

/**
 * Class used to work with the database and perform CRUD operations on the
 * Person type objects.
 * 
 * @author lucian.tuduce
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PersonService {

	@PersistenceContext(name = "CarProject", type = PersistenceContextType.TRANSACTION)
	public EntityManager entity;

	/**
	 * Method used to get all the persons from the database
	 * 
	 * @return list of persons
	 */
	public List<Person> getAll() {
		PersonConfig personConfig=null;
		TypedQuery<Person> query = entity.createNamedQuery(
				Person.NQ_Person_FIND_ALL, Person.class);
		List<Person> persons = query.getResultList();
		for (Person person : persons) {
			personConfig = new PersonConfig();
			personConfig.setFirstname(person.getFirstname());
			personConfig.setLastname(person.getLastname());
			personConfig.setPersonid(person.getPersonid());
			System.out.println(person.getFirstname() + " "
					+ person.getLastname());
			System.out.println("----------------------------");
			System.out.println(personConfig.getFirstname()+" "+personConfig.getLastname());
			System.out.println(personConfig.toString());
		}
		return persons;
	}

	/**
	 * Method user to return a specific person from the database based on the
	 * person id.
	 * 
	 * @param id
	 *            id of the person that will be returned from the database
	 * @return person who has the @param id
	 */
	public Person get(int id) {
		Person person = entity.find(Person.class, id);
		System.out.println(person.getFirstname() + " " + person.getLastname());
		return person;
	}

	/**
	 * Method used to add a person in the database
	 * 
	 * @param person
	 *            the person that will be added in the database
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insertPersonInDB(Person person) {
		entity.getTransaction().begin();
		entity.persist(person);
		entity.getTransaction().commit();
		//entity.flush();
	}

	/**
	 * Method user to delete a person from the database based on the person id.
	 * 
	 * @param personId
	 *            the id of the person that will be deleted
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deletePersonFromDB(int personId) {
		entity.getTransaction().begin();
		entity.remove(get(personId));
		entity.getTransaction().commit();
		//entity.flush();
	}

	/**
	 * Method used to update a specific person in the database
	 * 
	 * @param person
	 *            the data of the updated person in the database
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updatePersonInDB(Person person) {
		entity.getTransaction().begin();
		entity.merge(person);
		entity.getTransaction().commit();
		//entity.flush();
	}

}
