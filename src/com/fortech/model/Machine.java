package com.fortech.model;

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

	public static final String NQ_MACHINE_FIND_ALL = "Machine.findAll";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int machineid;

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