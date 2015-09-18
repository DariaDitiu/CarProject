package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Machine" database table.
 * 
 */
@Entity
@Table(name="\"Machine\"")
@NamedQuery(name="Machine.findAll", query="SELECT m FROM Machine m")
public class Machine implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String NQ_MACHINE_FIND_ALL = "Machine.findAllMachines";
	
	@Id
	@Column(name="\"MachineId\"")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int machineId;

	@Column(name="\"Model\"")
	private String model;

	public Machine() {
	}

	public int getMachineId() {
		return this.machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}