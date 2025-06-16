package org.learning.spring.boot.learning.exceptions;
/**
 * Custom exception to be thrown when a task is not found.
 * This exception can be used in the service layer to indicate that a requested task does not exist.
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}