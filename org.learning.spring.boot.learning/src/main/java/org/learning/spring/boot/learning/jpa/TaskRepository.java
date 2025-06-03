package org.learning.spring.boot.learning.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	/**
	 * By extending JpaRepository, TaskRepository automatically inherits
	 * many useful CRUD (Create, Read, Update, Delete) operations, such as
	 * 
	 * save(User user) – Insert or update a user.
	 * findById(Integer id) – Retrieve a user by ID.
	 * findAll() – Retrieve all users.
	 * deleteById(Integer id) – Delete a user by ID.
	 * count() – Get the total number of users.
	 * 
	 * If we want to implement more, we can.
	 */
	
	@Query(value = "SHOW TABLES", nativeQuery = true)
    List<Object[]> showAllTables();
}
