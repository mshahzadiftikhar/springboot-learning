package org.learning.spring.boot.learning.dto;

import jakarta.validation.constraints.NotEmpty;

public class PutTaskDTO {
	@NotEmpty
	private String name;
	@NotEmpty
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
