package com.fortech.jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MachineJAXB")
public class MachineConfig implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private int machineid;
	
	@XmlElement
	private String model;

	public int getMachineid() {
		return machineid;
	}

	public void setMachineid(int machineid) {
		this.machineid = machineid;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	
	

}
