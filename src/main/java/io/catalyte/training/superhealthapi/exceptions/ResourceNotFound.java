package io.catalyte.training.superhealthapi.exceptions;

/**
 * A custom exception for resource not found.
 */
public class ResourceNotFound extends RuntimeException {

  public ResourceNotFound() {
  }

  public ResourceNotFound(String message) {
    super(message);
  }
}
