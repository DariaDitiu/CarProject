package com.fortech.jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MachineJAXB")
@XmlAccessorType(XmlAccessType.FIELD)
public class MachineConfig implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private int machineid;
	
	@XmlElement(required=true)
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
