package org.learning.boot.basic.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.learning.boot.basic.jpa.Users;
import org.learning.boot.basic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserByID(@PathVariable Long id) {
		Optional<Users> op =  userService.getUserById(id);
		if (op.isPresent()) {
			return ResponseEntity.ok(op.get());
		} else {
			Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("message", "User with ID " + id + " not found");
	        errorResponse.put("status", HttpStatus.NOT_FOUND.value());

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse); // 404 with custom error message
		}
	}
	
	/**
	 * Adds new User to the database
	 * @param users
	 */
	@PostMapping("/users")
	public void addUser(@RequestBody Users users) { // This RequestBody will contains user data (in JSON form)
		userService.createUser(users);
	}
	
	@PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
        try {
            Users updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{message: \"ID not found\"}");
        }
    }

    // DELETE user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{message: \"ID not found\"}");
        }
    }
}
