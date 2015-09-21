package com.fortech.jaxb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.Machine;

@XmlRootElement(name="PersonJAXB")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonConfig implements Serializable{

	private static final long serialVersionUID = 1L;

	@XmlElement(required=true)
	private int personid;
	@XmlElement(required=true)
	private String firstname;
	@XmlElement(required=true)
	private String lastname;
	@XmlElement(name="Machine", required=true)
	private List<Machine> machines;

	private static List<PersonConfig> allPersons = new ArrayList<>();
	
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

	public static List<PersonConfig> getAllPersons() {
		return allPersons;
	}

	public static void setAllPersons(List<PersonConfig> allPersons) {
		PersonConfig.allPersons = allPersons;
	}
	
	public static void addInLIst(PersonConfig p){
		allPersons.add(p);
	}
}
