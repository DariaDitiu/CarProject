package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Person" database table.
 * 
 */
@Entity
@Table(name="\"Person\"")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String NQ_Person_FIND_ALL = "Person.findAll";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int personid;

	@Column(length=45)
	private String firstname;

	@Column(length=45)
	private String lastname;

	//bi-directional many-to-one association to Machine
	@OneToMany(mappedBy="person")
	private List<Machine> machines;

	public Person() {
	}

	public int getPersonid() {
		return this.personid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Machine> getMachines() {
		return this.machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}

	public Machine addMachine(Machine machine) {
		getMachines().add(machine);
		machine.setPerson(this);

		return machine;
	}

	public Machine removeMachine(Machine machine) {
		getMachines().remove(machine);
		machine.setPerson(null);

		return machine;
	}

}