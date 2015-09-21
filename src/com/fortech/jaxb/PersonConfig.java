package com.fortech.jaxb;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.Machine;

@XmlRootElement(name="PersonJAXB")
public class PersonConfig implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement
	private int personid;
	@XmlElement
	private String firstname;
	@XmlElement
	private String lastname;
	@XmlElement(name="Machine")
	private List<Machine> machines;

	public PersonConfig() {
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

//	public Machine addMachine(Machine machine) {
//		getMachines().add(machine);
//		machine.setPerson(this);
//
//		return machine;
//	}
//
//	public Machine removeMachine(Machine machine) {
//		getMachines().remove(machine);
//		machine.setPerson(null);
//
//		return machine;
//	}
//	
}
