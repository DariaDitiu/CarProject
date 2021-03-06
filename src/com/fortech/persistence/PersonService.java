package com.fortech.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import com.fortech.jaxb.PersonConfig;
import com.fortech.model.Person;

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
	public EntityManager entityManager;

	/**
	 * Method used to get all the persons from the database
	 * 
	 * @return list of persons
	 */
	public List<Person> getAll() {
		TypedQuery<Person> query = entityManager.createNamedQuery(
				Person.NQ_Person_FIND_ALL, Person.class);
		return query.getResultList();
	}

	/**
	 * Method user to get the data from db and add it in the JAXB class.
	 */
	public List<PersonConfig> getFromDBdataToXML() {
		PersonConfig personConfig = null;
		TypedQuery<Person> query = entityManager.createNamedQuery(
				Person.NQ_Person_FIND_ALL, Person.class);
		List<PersonConfig> personConfigs = new ArrayList<PersonConfig>();
		for (Person person : query.getResultList()) {
			personConfig = new PersonConfig();
			personConfig.setFirstname(person.getFirstname());
			personConfig.setLastname(person.getLastname());
			personConfig.setPersonid(person.getPersonid());
			personConfigs.add(personConfig);
		}
		return personConfigs;
	}

	/**
	 * Method user to return a specific person from the database based on the
	 * person id.
	 * 
	 * @param id
	 *            id of the person that will be returned from the database
	 * @return person who has the @param id
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Person get(int id) {
		Person person = entityManager.find(Person.class, id);
		return person;
	}

	/**
	 * Method used to add a person in the database
	 * 
	 * @param person
	 *            the person that will be added in the database
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insertPersonInDB(PersonConfig person) {

		Person p = new Person();
		p.setFirstname(person.getFirstname());
		p.setLastname(person.getLastname());

		entityManager.getTransaction().begin();
		entityManager.persist(p);
		entityManager.getTransaction().commit();
	}

	/**
	 * Method user to delete a person from the database based on the person id.
	 * 
	 * @param personId
	 *            the id of the person that will be deleted
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deletePersonFromDB(int personId) {
		entityManager.getTransaction().begin();
		entityManager.remove(get(personId));
		entityManager.getTransaction().commit();
	}

	/**
	 * Method used to update a specific person in the database
	 * 
	 * @param person
	 *            the data of the updated person in the database
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updatePersonInDB(PersonConfig personConfig) {
		Person person = new Person();
		person.setFirstname(personConfig.getFirstname());
		person.setLastname(personConfig.getLastname());

		entityManager.getTransaction().begin();
		entityManager.merge(person);
		entityManager.getTransaction().commit();
	}

	/**
	 * Method used to add a person in the database
	 * 
	 * @param person
	 *            the person that will be added in the database
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insertPersonInDB(Person person) {
		entityManager.getTransaction().begin();
		entityManager.persist(person);
		entityManager.getTransaction().commit();

	}

	/**
	 * Method used to update a specific person in the database
	 * 
	 * @param person
	 *            the data of the updated person in the database
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updatePersonInDB(Person person) {
		entityManager.getTransaction().begin();
		entityManager.merge(person);
		entityManager.getTransaction().commit();
	}

}
