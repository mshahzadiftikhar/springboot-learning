package org.learning.boot.basic.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
	/**
	 * By extending JpaRepository, UserRepository automatically inherits
	 * many useful CRUD (Create, Read, Update, Delete) operations, such as
	 * 
	 * save(User user) – Insert or update a user.
	 * findById(Long id) – Retrieve a user by ID.
	 * findAll() – Retrieve all users.
	 * deleteById(Long id) – Delete a user by ID.
	 * count() – Get the total number of users.
	 * 
	 * If we want to implement more, we can.
	 */
}
