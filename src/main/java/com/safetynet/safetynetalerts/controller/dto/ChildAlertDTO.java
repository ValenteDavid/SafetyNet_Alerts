package com.safetynet.safetynetalerts.controller.dto;

import java.util.Set;

public class ChildAlertDTO {

	private Set<PersonChildAlertDTO> setChildren;

	public ChildAlertDTO() {
		super();
	}

	public ChildAlertDTO(Set<PersonChildAlertDTO> setChildren) {
		super();
		this.setChildren = setChildren;
	}

	public Set<PersonChildAlertDTO> getSetChildren() {
		return setChildren;
	}

	public void setListChildren(Set<PersonChildAlertDTO> setChildren) {
		this.setChildren = setChildren;
	}

	@Override
	public String toString() {
		return "ChildAlertDTO [ListChildren=" + setChildren + "]";
	}

}
