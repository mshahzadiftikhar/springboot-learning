package org.learning.spring.boot.learning.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // tells spring boot that its a table
@Table(name = "Task") // [Optional]: By default table name is class name, but can be overridden
public class Task {
	
	@Id // indicates that its a primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String name;
	public String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Task() {
		
	}
}
