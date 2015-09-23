package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the MACHINE database table.
 * 
 */
@Entity
//@NamedQuery(name="Machine.findAll", query="SELECT m FROM Machine m")
@NamedQueries({ @NamedQuery(name = Machine.NQ_MACHINE_FIND_ALL, query = "SELECT m FROM Machine m")})
public class Machine implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String NQ_MACHINE_FIND_ALL = "Machine.findAllMachines";

	@Id
	private int machineid;

	private String model;

	@ManyToOne
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

}