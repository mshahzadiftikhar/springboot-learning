package org.learning.spring.boot.learning.dto;

public class ResponseTaskDTO {
	private int id;
	private String name;

	public ResponseTaskDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
