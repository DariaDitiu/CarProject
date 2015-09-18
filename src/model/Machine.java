package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Machine" database table.
 * 
 */
@Entity
@Table(name="\"Machine\"")
@NamedQueries({ @NamedQuery(name = Machine.NQ_MACHINE_FIND_ALL, query = "SELECT m FROM Machine m")})
public class Machine implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String NQ_MACHINE_FIND_ALL = "Machine.findAllMachines";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int machineid;

	@Column(length=45)
	private String model;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="PERSONID")
	private Person person;

	public Machine() {
	}

	public int getMachineid() {
		return this.machineid;
	}

	public void setMachineid(int machineid) {
		this.machineid = machineid;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}